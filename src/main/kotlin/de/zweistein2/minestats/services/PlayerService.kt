package de.zweistein2.minestats.services

import com.google.gson.Gson
import com.google.gson.JsonObject
import de.zweistein2.minestats.components.MedalComparator
import de.zweistein2.minestats.components.MinestatProperties
import de.zweistein2.minestats.components.PlayerStatComparator
import de.zweistein2.minestats.models.ServerPlayerModel
import de.zweistein2.minestats.models.minecraftstats.CategoryKeys
import de.zweistein2.minestats.models.minecraftstats.CustomKeys
import de.zweistein2.minestats.models.minecraftstats.MinecraftStatsModel
import de.zweistein2.minestats.repositories.ServerPlayerHistoricRepository
import de.zweistein2.minestats.repositories.ServerPlayerRepository
import de.zweistein2.minestats.repositories.entities.ServerPlayerEntity
import de.zweistein2.minestats.repositories.entities.ServerPlayerHistoricEntity
import de.zweistein2.minestats.utils.MedalAggregatorUtil
import de.zweistein2.minestats.utils.StatAggregatorUtil
import de.zweistein2.torque.spring.MonitoringSpringWrapper
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import org.springframework.stereotype.Service

private const val maxMedalPlayerCount = 10

@Service
class PlayerService(
    val serverPlayerRepository: ServerPlayerRepository,
    val serverPlayerHistoricRepository: ServerPlayerHistoricRepository,
    val minestatProperties: MinestatProperties,
    val banService: BanService,
) {
    val monitoring = MonitoringSpringWrapper(true).getMonitoring()

    companion object {
        fun ServerPlayerEntity.convertEntityToModel(): ServerPlayerModel = ServerPlayerModel(
            UUID.fromString(this.uuid), this.username ?: "",
            LocalDateTime.ofInstant(Instant.ofEpochSecond(this.changed ?: 0), ZoneId.of("UTC")),
            if(Gson().fromJson(this.stats_json, JsonObject::class.java).has("stats")) {
                MinecraftStatsModel(Gson().fromJson(this.stats_json, JsonObject::class.java)["stats"].asJsonObject)
            } else {
                MinecraftStatsModel(Gson().fromJson(this.stats_json, JsonObject::class.java).asJsonObject)
            }
        )

        fun ServerPlayerHistoricEntity.convertEntityToModel(): ServerPlayerModel = ServerPlayerModel(
            UUID.fromString(this.uuid), this.username ?: "",
            LocalDateTime.ofInstant(Instant.ofEpochSecond(this.changed ?: 0), ZoneId.of("UTC")),
            if(Gson().fromJson(this.stats_json, JsonObject::class.java).has("stats")) {
                MinecraftStatsModel(Gson().fromJson(this.stats_json, JsonObject::class.java)["stats"].asJsonObject)
            } else {
                MinecraftStatsModel(Gson().fromJson(this.stats_json, JsonObject::class.java).asJsonObject)
            }
        )
    }

    fun loadUnbannedHistoricPlayers(): List<ServerPlayerModel> {
        return monitoring.withTimer("loadAllHistoricPlayers", "database") {
            val players = serverPlayerHistoricRepository.findAll()
            val bans = banService.loadBans()

            players.stream().filter { it.stats_json.isNotBlank() }.map { it.convertEntityToModel() }
                .filter { player -> !bans.map { it.uuid }.contains(player.uuid) }.toList() ?: listOf()
        }
    }

    @Throws(IllegalStateException::class)
    fun loadHistoricPlayer(username: String, throwExceptionIfNull: Boolean): ServerPlayerModel? {
        return monitoring.withTimer("loadHistoricPlayer", "database", Pair("playerName", username)) {
            if (throwExceptionIfNull)
                serverPlayerHistoricRepository.findByUsername(username).map { it.convertEntityToModel() }
                    .orElseThrow { IllegalStateException("Der Benutzer mit dem Namen $username existiert nicht") }
            else
                serverPlayerHistoricRepository.findByUsername(username).map { it.convertEntityToModel() }.orElse(null)
        }
    }

    fun loadAllPlayers(): List<ServerPlayerModel> {
        return monitoring.withTimer("loadAllPlayers", "database") {
            val players = serverPlayerRepository.findAll()

            players.stream().filter { !it.username.isNullOrBlank() }.map { it.convertEntityToModel() }.toList()
                ?: listOf()
        }
    }

    @Throws(IllegalStateException::class)
    fun loadPlayer(username: String, throwExceptionIfNull: Boolean): ServerPlayerModel? {
        return monitoring.withTimer("loadPlayer", "database", Pair("playerName", username)) {
            if (throwExceptionIfNull)
                serverPlayerRepository.findByUsername(username).map { it.convertEntityToModel() }
                    .orElseThrow { IllegalStateException("Der Benutzer mit dem Namen $username existiert nicht") }
            else
                serverPlayerRepository.findByUsername(username).map { it.convertEntityToModel() }.orElse(null)
        }
    }

    fun loadOnlinePlayers(): List<ServerPlayerModel> {
        return monitoring.withTimer("loadOnlinePlayers", "database") {
            val status = MinecraftQuery(minestatProperties.queryIp, minestatProperties.queryPort).status()
            val playerlist = status?.playerInfo?.players?.toList()?.stream()?.map { it.name }?.toList() ?: listOf()

            val players = serverPlayerRepository.findAll()

            players.filter { !it.username.isNullOrBlank() && playerlist.contains(it.username) }
                .map { it.convertEntityToModel() }.toList()
        }
    }

    fun getAllMedals(): List<Pair<String, Triple<Int, Int, Int>>> {
        return monitoring.withTimer("getAllMedals", "database") {
            val bans = banService.loadBans()
            val players = serverPlayerRepository.findAll().stream().map { it.convertEntityToModel() }
                .filter { player -> !bans.map { it.uuid }.contains(player.uuid) }.toList() ?: listOf()
            val allMedals = mutableMapOf<String, Triple<Int, Int, Int>>()

            for (stat in CustomKeys.values()) {
                val topThreePlayers = players.sortedWith(PlayerStatComparator(CategoryKeys.CUSTOM, stat))
                    .filter { it.stats.forCategory(CategoryKeys.CUSTOM).forStat(stat) != 0L }.take(3)

                MedalAggregatorUtil.accountMedalsForTopThreePlayers(topThreePlayers, allMedals)
            }

            MedalAggregatorUtil.accountMedalsForTopThreePlayers(StatAggregatorUtil.getBlockStatsPerPlayer(players, CategoryKeys.MINED, 3).map { it.key }.toList(), allMedals)
            MedalAggregatorUtil.accountMedalsForTopThreePlayers(StatAggregatorUtil.getBlockStatsPerPlayer(players, CategoryKeys.USED, 3).map { it.key }.toList(), allMedals)
            MedalAggregatorUtil.accountMedalsForTopThreePlayers(StatAggregatorUtil.getBlockStatsPerPlayer(players, CategoryKeys.PICKED_UP, 3).map { it.key }.toList(), allMedals)
            MedalAggregatorUtil.accountMedalsForTopThreePlayers(StatAggregatorUtil.getBlockStatsPerPlayer(players, CategoryKeys.CRAFTED, 3).map { it.key }.toList(), allMedals)
            MedalAggregatorUtil.accountMedalsForTopThreePlayers(StatAggregatorUtil.getBlockStatsPerPlayer(players, CategoryKeys.DROPPED, 3).map { it.key }.toList(), allMedals)

            MedalAggregatorUtil.accountMedalsForTopThreePlayers(StatAggregatorUtil.getItemStatsPerPlayer(players, CategoryKeys.BROKEN, 3).map { it.key }.toList(), allMedals)
            MedalAggregatorUtil.accountMedalsForTopThreePlayers(StatAggregatorUtil.getItemStatsPerPlayer(players, CategoryKeys.PICKED_UP, 3).map { it.key }.toList(), allMedals)
            MedalAggregatorUtil.accountMedalsForTopThreePlayers(StatAggregatorUtil.getItemStatsPerPlayer(players, CategoryKeys.DROPPED, 3).map { it.key }.toList(), allMedals)
            MedalAggregatorUtil.accountMedalsForTopThreePlayers(StatAggregatorUtil.getItemStatsPerPlayer(players, CategoryKeys.CRAFTED, 3).map { it.key }.toList(), allMedals)
            MedalAggregatorUtil.accountMedalsForTopThreePlayers(StatAggregatorUtil.getItemStatsPerPlayer(players, CategoryKeys.USED, 3).map { it.key }.toList(), allMedals)

            allMedals.toList().sortedWith(MedalComparator()).reversed()
        }
    }

    fun getTopTenPlayers(): List<Pair<String, Triple<Int, Int, Int>>> {
        return monitoring.withTimer("getTopTenPlayers", "database") {
            getAllMedals().take(maxMedalPlayerCount)
        }
    }

    fun getMedalsForPlayer(playername: String): Triple<Int, Int, Int> {
        return monitoring.withTimer("getMedalsForPlayer", "database", Pair("playerName", playername)) {
            getAllMedals().find { it.first == playername }?.second ?: Triple(0, 0, 0)
        }
    }
}