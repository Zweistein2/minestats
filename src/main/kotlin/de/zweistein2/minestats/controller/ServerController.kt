package de.zweistein2.minestats.controller

import de.zweistein2.minestats.components.MinestatProperties
import de.zweistein2.minestats.components.MulticraftProperties
import de.zweistein2.minestats.models.ServerStatsModel
import de.zweistein2.minestats.models.serverstats.ServerStatsInfoModel
import de.zweistein2.minestats.models.serverstats.ServerStatsMetaModel
import de.zweistein2.minestats.services.MinecraftQuery
import de.zweistein2.minestats.services.MulticraftAPI
import de.zweistein2.minestats.services.ServerService
import mu.KotlinLogging
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.time.LocalDateTime
import java.util.*
import kotlin.math.ceil
import kotlin.math.roundToInt
import kotlin.system.measureTimeMillis

@Controller
@CacheConfig(cacheNames = ["serverStats"])
class ServerController(
    val serverService: ServerService,
    val multicraftProperties: MulticraftProperties,
    val minestatProperties: MinestatProperties
) {
    companion object {
        fun String.capitalize(): String {
            return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        }
    }

    @Cacheable
    @GetMapping("/server")
    fun getServerStats(model: Model): String {
        val runtimeInMilliseconds = measureTimeMillis {
            val multicraftAPI = MulticraftAPI(multicraftProperties.apiUrl, multicraftProperties.user, multicraftProperties.key)

            val serverInfo = multicraftAPI.getServer(multicraftProperties.serverId)
            val serverResources = multicraftAPI.getServerResources(multicraftProperties.serverId)
            val serverStatus = multicraftAPI.getServerStatus(multicraftProperties.serverId, true)

            val serverStatsHistory = serverService.loadHistory()
            val donationsPerMonth = serverService.getDonationsPerMonth()

            val maxMemoryUsage = serverInfo?.data?.server?.maxMemory ?: 0
            val currentMemoryUsage = ((maxMemoryUsage.times(serverResources?.data?.memory ?: 1.0f)) / 100.0f).roundToInt()
            val latestServerHistoryStats = if(serverStatsHistory.isNotEmpty()) serverStatsHistory.last() else ServerStatsModel(
                LocalDateTime.now(), 0, 0, 0, 0.0f, 0, Properties())
            val worldSize = latestServerHistoryStats.worldsize.toDouble().div(1024.0).div(1024.0).div(1024.0).toFloat()
            val tps = latestServerHistoryStats.tps
            val maxPlayers = (latestServerHistoryStats.properties["max-players"] as String).toInt()

            val serverStatsMeta = ServerStatsMetaModel(serverStatus?.data?.onlinePlayers ?: 0, worldSize, currentMemoryUsage, maxMemoryUsage, tps, serverResources?.data?.cpu ?: 0.0f)

            val serverStatusInfo = MinecraftQuery(minestatProperties.queryIp, minestatProperties.queryPort).status()

            val playerlist = serverStatusInfo?.playerInfo?.players?.toList()?.stream()?.map { it.name }?.toList() ?: listOf()
            val seed = latestServerHistoryStats.properties["level-seed"] as String

            val serverStatsInfo = ServerStatsInfoModel(
                latestServerHistoryStats.properties["server-name"] as String,
                serverStatusInfo?.versionInfo?.versionName?.split(" ")?.get(1)?.trim() ?: "",
                serverStatusInfo?.versionInfo?.versionName?.split(" ")?.get(0)?.trim() ?: "",
                maxPlayers,
                playerlist,
                (latestServerHistoryStats.properties["gamemode"] as String).capitalize(),
                latestServerHistoryStats.properties["level-name"] as String,
                if(minestatProperties.hideSeed) "" else seed,
                (latestServerHistoryStats.properties["difficulty"] as String).capitalize(),
                (latestServerHistoryStats.properties["spawn-monsters"] as String).toBoolean(),
                (latestServerHistoryStats.properties["spawn-npcs"] as String).toBoolean(),
                (latestServerHistoryStats.properties["spawn-animals"] as String).toBoolean(),
                (latestServerHistoryStats.properties["view-distance"] as String).toInt(),
                (latestServerHistoryStats.properties["pvp"] as String).toBoolean(),
                (latestServerHistoryStats.properties["white-list"] as String).toBoolean(),
            )

            val serverStatsHistoryForDays = serverStatsHistory.stream().filter { it.timestamp.isAfter(LocalDateTime.now().minusDays(minestatProperties.historyDays)) }.toList()

            val playerChartPayload = serverService.getChartPayload("Spieler", "Spieler", "Spieler", 0, maxPlayers,
                serverStatsHistoryForDays.map { it.timestamp.toString() }.toList(),
                serverStatsHistoryForDays.map { it.players }.toList())
            val tpsChartPayload = serverService.getChartPayload("TPS", "TPS", "TPS", 0.0f, 20.0f,
                serverStatsHistoryForDays.map { it.timestamp.toString() }.toList(),
                serverStatsHistoryForDays.map { it.tps }.toList())
            val cpuChartPayload = serverService.getChartPayload("Prozessorauslastung", "CPU", "%", 0, 100 * minestatProperties.coreCount,
                serverStatsHistoryForDays.map { it.timestamp.toString() }.toList(),
                serverStatsHistoryForDays.map { it.cpu }.toList())
            val ramChartPayload = serverService.getChartPayload("Speicherauslastung", "RAM", "MB", 0, maxMemoryUsage,
                serverStatsHistoryForDays.map { it.timestamp.toString() }.toList(),
                serverStatsHistoryForDays.map { it.ram }.toList())
            val worldSizeChartPayload = serverService.getChartPayload("Weltengröße", "GB", "GB", 0, ceil(worldSize).toInt(),
                serverStatsHistory.stream().map { it.timestamp.toString() }.toList(),
                serverStatsHistory.stream().map { it.worldsize.toDouble().div(1024.0).div(1024.0).div(1024.0).toFloat() }.toList())
            val donationsChartPayload = serverService.getChartPayload("Spenden", "Spenden", "€ (Euro)", 0, -1,
                donationsPerMonth.keys.toList().map { it.toString() }.toList(), donationsPerMonth.values.toList())

            model.addAttribute("playerChart", playerChartPayload)
            model.addAttribute("tpsChart", tpsChartPayload)
            model.addAttribute("cpuChart", cpuChartPayload)
            model.addAttribute("ramChart", ramChartPayload)
            model.addAttribute("worldSizeChart", worldSizeChartPayload)
            model.addAttribute("donationsChart", donationsChartPayload)

            model.addAttribute("playerlist", playerlist.ifEmpty { listOf("Niemand") })
            model.addAttribute("meta", serverStatsMeta)
            model.addAttribute("info", serverStatsInfo)
        }

        model.addAttribute("runtime", runtimeInMilliseconds)

        return "server"
    }
}