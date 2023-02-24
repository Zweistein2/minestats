package de.zweistein2.minestats.controller

import de.zweistein2.minestats.components.MinestatProperties
import de.zweistein2.minestats.models.minecraftstats.CategoryKeys
import de.zweistein2.minestats.models.minecraftstats.CustomKeys
import de.zweistein2.minestats.models.minecraftstats.MobKeys
import de.zweistein2.minestats.services.PlayerService
import de.zweistein2.minestats.utils.StatAggregatorUtil
import de.zweistein2.torque.spring.MonitoringSpringWrapper
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.system.measureTimeMillis
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

private const val MAX_LAST_PLAYED_COUNT = 20
private const val TICKS_PER_SECOND = 20
private const val SECONDS_PER_MINUTE = 60

@Controller
@CacheConfig(cacheNames = ["playerStats"])
class PlayerController(
    val playerService: PlayerService,
    val minestatProperties: MinestatProperties
) {
    val monitoring = MonitoringSpringWrapper(true).getMonitoring()

    companion object {
        @JvmStatic
        fun Long.timeFormat(locale: Locale): String {
            val bundle = ResourceBundle.getBundle("lang/messages", locale)
            val duration = Duration.ofMinutes(this)

            return "${duration.toDaysPart()} ${bundle.getString("days")}, ${duration.toHoursPart()} ${bundle.getString("hours")} ${
                bundle.getString(
                    "and"
                )
            } ${duration.toMinutesPart()} ${bundle.getString("minutes")}"
        }

        @JvmStatic
        fun LocalDateTime.timeFormat(locale: Locale): String {
            val bundle = ResourceBundle.getBundle("lang/messages", locale)
            val duration = Duration.between(this, LocalDateTime.now(ZoneId.of("UTC")))

            return "${duration.toDaysPart()} ${bundle.getString("days")}, ${duration.toHoursPart()} ${bundle.getString("hours")} ${
                bundle.getString(
                    "and"
                )
            } ${duration.toMinutesPart()} ${bundle.getString("minutes")}"
        }
    }

    @Cacheable
    @GetMapping("/player")
    fun getPlayerStats(model: Model, @RequestParam playername: String?, locale: Locale = Locale.forLanguageTag(minestatProperties.locale)): String {
        val runtimeInMilliseconds = measureTimeMillis {
            if(playername.isNullOrBlank()) {
                monitoring.withTimer("showAllPlayers", "frontend") {
                    val players = playerService.loadAllPlayers()

                    val playerCount = players.size
                    val playersOnline = playerService.loadOnlinePlayers().size
                    val playersOnlineThirtyDaysCount = players.filter {
                        it.changed != null && it.changed.isAfter(
                            LocalDateTime.now(ZoneId.of("UTC")).minusDays(30)
                        )
                    }.size
                    val twenthyLastOnlinePlayers = players.sortedByDescending { it.changed }.take(MAX_LAST_PLAYED_COUNT)
                        .associate {
                            it.username to ChronoUnit.MINUTES.between(
                                LocalDateTime.now(ZoneId.of("UTC")),
                                it.changed
                            ).times(-1L).timeFormat(locale)
                        }

                    // TotalTimeInTicks / 20 Ticks/Sec / 60 Sec/Min / CountLeaveGame
                    var averagePlayingTimeInMinutes = 0L
                    for (player in players) {
                        val leftGames = if (player.stats.forCategory(CategoryKeys.CUSTOM)
                                .forStat(CustomKeys.LEAVE_GAME) == 0L
                        ) 1L else player.stats.forCategory(CategoryKeys.CUSTOM).forStat(CustomKeys.LEAVE_GAME)

                        averagePlayingTimeInMinutes += player.stats.forCategory(CategoryKeys.CUSTOM)
                            .forStat(CustomKeys.TOTAL_WORLD_TIME) / TICKS_PER_SECOND / SECONDS_PER_MINUTE / leftGames
                    }
                    averagePlayingTimeInMinutes /= players.size

                    model.addAttribute("playersOnline", playersOnline)
                    model.addAttribute("playerCount", playerCount)
                    model.addAttribute("playersOnlineThirtyDaysCount", playersOnlineThirtyDaysCount)
                    model.addAttribute("averagePlayingTimeInMinutes", averagePlayingTimeInMinutes.timeFormat(locale))
                    model.addAttribute("twenthyLastOnlinePlayers", twenthyLastOnlinePlayers)
                    model.addAttribute("topTenPlayers", playerService.getTopTenPlayers())
                }
            } else {
                monitoring.withTimer("showSpecificPlayer", "frontend", Pair("playerName", playername)) {
                    val player = playerService.loadPlayer(playername, true)!!
                    val historicPlayer = playerService.loadHistoricPlayer(playername, false)

                    val leftGames = if (player.stats.forCategory(CategoryKeys.CUSTOM)
                            .forStat(CustomKeys.LEAVE_GAME) == 0L
                    ) 1L else player.stats.forCategory(CategoryKeys.CUSTOM).forStat(CustomKeys.LEAVE_GAME)
                    // TotalTimeInTicks / 20 Ticks/Sec / 60 Sec/Min / CountLeaveGame
                    val averagePlayingTimeInMinutes = player.stats.forCategory(CategoryKeys.CUSTOM)
                        .forStat(CustomKeys.TOTAL_WORLD_TIME) / TICKS_PER_SECOND / SECONDS_PER_MINUTE / leftGames

                    val killedMobs = mutableListOf<Pair<String, Long>>()
                    for (mobKey in MobKeys.values()) {
                        killedMobs.add(mobKey.name to player.stats.forCategory(CategoryKeys.KILLED).forStat(mobKey))
                    }
                    killedMobs.removeIf { it.second == 0L }
                    killedMobs.sortByDescending { it.second }

                    val killedByMobs = mutableListOf<Pair<String, Long>>()
                    for (mobKey in MobKeys.values()) {
                        killedByMobs.add(
                            mobKey.name to player.stats.forCategory(CategoryKeys.KILLED_BY).forStat(mobKey)
                        )
                    }
                    killedByMobs.removeIf { it.second == 0L }
                    killedByMobs.sortByDescending { it.second }

                    var overallPlayingTime = (player.stats.forCategory(CategoryKeys.CUSTOM)
                        .forStat(CustomKeys.TOTAL_WORLD_TIME) / TICKS_PER_SECOND / SECONDS_PER_MINUTE)

                    if (historicPlayer != null) {
                        overallPlayingTime += (historicPlayer.stats.forCategory(CategoryKeys.CUSTOM)
                            .forStat(CustomKeys.TOTAL_WORLD_TIME) / TICKS_PER_SECOND / SECONDS_PER_MINUTE)
                    }

                    val totalMinedBlocks = StatAggregatorUtil.getTotalBlockStatsForPlayer(player, CategoryKeys.MINED)
                    val totalPlacedBlocks = StatAggregatorUtil.getTotalBlockStatsForPlayer(player, CategoryKeys.USED)
                    val totalPickedUpBlocks = StatAggregatorUtil.getTotalBlockStatsForPlayer(player, CategoryKeys.PICKED_UP)
                    val totalCraftedBlocks = StatAggregatorUtil.getTotalBlockStatsForPlayer(player, CategoryKeys.CRAFTED)
                    val totalDroppedBlocks = StatAggregatorUtil.getTotalBlockStatsForPlayer(player, CategoryKeys.DROPPED)

                    val totalBrokenItems = StatAggregatorUtil.getTotalItemStatsForPlayer(player, CategoryKeys.BROKEN)
                    val totalPickedUpItems = StatAggregatorUtil.getTotalItemStatsForPlayer(player, CategoryKeys.PICKED_UP)
                    val totalDroppedItems = StatAggregatorUtil.getTotalItemStatsForPlayer(player, CategoryKeys.DROPPED)
                    val totalCraftedItems = StatAggregatorUtil.getTotalItemStatsForPlayer(player, CategoryKeys.CRAFTED)
                    val totalUsedItems = StatAggregatorUtil.getTotalItemStatsForPlayer(player, CategoryKeys.USED)

                    val minedBlocks = StatAggregatorUtil.getBlockStatsForPlayer(player, CategoryKeys.MINED)
                    val placedBlocks = StatAggregatorUtil.getBlockStatsForPlayer(player, CategoryKeys.USED)
                    val pickedUpBlocks = StatAggregatorUtil.getBlockStatsForPlayer(player, CategoryKeys.PICKED_UP)
                    val craftedBlocks = StatAggregatorUtil.getBlockStatsForPlayer(player, CategoryKeys.CRAFTED)
                    val droppedBlocks = StatAggregatorUtil.getBlockStatsForPlayer(player, CategoryKeys.DROPPED)

                    val brokenItems = StatAggregatorUtil.getItemStatsForPlayer(player, CategoryKeys.BROKEN)
                    val pickedUpItems = StatAggregatorUtil.getItemStatsForPlayer(player, CategoryKeys.PICKED_UP)
                    val droppedItems = StatAggregatorUtil.getItemStatsForPlayer(player, CategoryKeys.DROPPED)
                    val craftedItems = StatAggregatorUtil.getItemStatsForPlayer(player, CategoryKeys.CRAFTED)
                    val usedItems = StatAggregatorUtil.getItemStatsForPlayer(player, CategoryKeys.USED)

                    model.addAttribute("player", player)
                    model.addAttribute(
                        "lastOnlineInMinutes",
                        player.changed?.timeFormat(locale) ?: LocalDateTime.now(ZoneId.of("UTC")).timeFormat(locale)
                    )
                    model.addAttribute(
                        "playingTimeInMinutes",
                        (player.stats.forCategory(CategoryKeys.CUSTOM)
                            .forStat(CustomKeys.TOTAL_WORLD_TIME) / TICKS_PER_SECOND / SECONDS_PER_MINUTE).timeFormat(
                            locale
                        )
                    )
                    model.addAttribute("overallPlayingTimeInMinutes", overallPlayingTime.timeFormat(locale))
                    model.addAttribute("averagePlayingTimeInMinutes", averagePlayingTimeInMinutes.timeFormat(locale))
                    model.addAttribute("playerMedals", playerService.getMedalsForPlayer(playername))
                    model.addAttribute("killedMobsList", killedMobs)
                    model.addAttribute("killedByMobsList", killedByMobs)
                    model.addAttribute("totalMinedBlocks", totalMinedBlocks)
                    model.addAttribute("totalPlacedBlocks", totalPlacedBlocks)
                    model.addAttribute("totalPickedUpBlocks", totalPickedUpBlocks)
                    model.addAttribute("totalCraftedBlocks", totalCraftedBlocks)
                    model.addAttribute("totalDroppedBlocks", totalDroppedBlocks)
                    model.addAttribute("totalBrokenItems", totalBrokenItems)
                    model.addAttribute("totalPickedUpItems", totalPickedUpItems)
                    model.addAttribute("totalDroppedItems", totalDroppedItems)
                    model.addAttribute("totalCraftedItems", totalCraftedItems)
                    model.addAttribute("totalUsedItems", totalUsedItems)
                    model.addAttribute("minedBlocks", minedBlocks)
                    model.addAttribute("placedBlocks", placedBlocks)
                    model.addAttribute("pickedUpBlocks", pickedUpBlocks)
                    model.addAttribute("craftedBlocks", craftedBlocks)
                    model.addAttribute("droppedBlocks", droppedBlocks)
                    model.addAttribute("brokenItems", brokenItems)
                    model.addAttribute("pickedUpItems", pickedUpItems)
                    model.addAttribute("droppedItems", droppedItems)
                    model.addAttribute("craftedItems", craftedItems)
                    model.addAttribute("usedItems", usedItems)
                }
            }
        }

        model.addAttribute("runtime", runtimeInMilliseconds)

        return "player"
    }

    @GetMapping("/search")
    fun searchSpecificPlayer(model: Model, @RequestParam playername: String, locale: Locale = Locale.forLanguageTag(minestatProperties.locale)): String {
        val runtimeInMilliseconds = measureTimeMillis {
            monitoring.withTimer("searchSpecificPlayer", "frontend", Pair("playerName", playername)) {
                val player = playerService.loadPlayer(playername, true)!!
                val historicPlayer = playerService.loadHistoricPlayer(playername, false)

                val leftGames = if (player.stats.forCategory(CategoryKeys.CUSTOM)
                        .forStat(CustomKeys.LEAVE_GAME) == 0L
                ) 1L else player.stats.forCategory(CategoryKeys.CUSTOM).forStat(CustomKeys.LEAVE_GAME)
                // TotalTimeInTicks / 20 Ticks/Sec / 60 Sec/Min / CountLeaveGame
                val averagePlayingTimeInMinutes = player.stats.forCategory(CategoryKeys.CUSTOM)
                    .forStat(CustomKeys.TOTAL_WORLD_TIME) / TICKS_PER_SECOND / SECONDS_PER_MINUTE / leftGames

                val killedMobs = mutableListOf<Pair<String, Long>>()
                for (mobKey in MobKeys.values()) {
                    killedMobs.add(mobKey.name to player.stats.forCategory(CategoryKeys.KILLED).forStat(mobKey))
                }
                killedMobs.removeIf { it.second == 0L }
                killedMobs.sortByDescending { it.second }

                val killedByMobs = mutableListOf<Pair<String, Long>>()
                for (mobKey in MobKeys.values()) {
                    killedByMobs.add(
                        mobKey.name to player.stats.forCategory(CategoryKeys.KILLED_BY).forStat(mobKey)
                    )
                }
                killedByMobs.removeIf { it.second == 0L }
                killedByMobs.sortByDescending { it.second }

                var overallPlayingTime = (player.stats.forCategory(CategoryKeys.CUSTOM)
                    .forStat(CustomKeys.TOTAL_WORLD_TIME) / TICKS_PER_SECOND / SECONDS_PER_MINUTE)

                if (historicPlayer != null) {
                    overallPlayingTime += (historicPlayer.stats.forCategory(CategoryKeys.CUSTOM)
                        .forStat(CustomKeys.TOTAL_WORLD_TIME) / TICKS_PER_SECOND / SECONDS_PER_MINUTE)
                }

                model.addAttribute("player", player)
                model.addAttribute(
                    "lastOnlineInMinutes",
                    player.changed?.timeFormat(locale) ?: LocalDateTime.now(ZoneId.of("UTC")).timeFormat(locale)
                )
                model.addAttribute(
                    "playingTimeInMinutes",
                    (player.stats.forCategory(CategoryKeys.CUSTOM)
                        .forStat(CustomKeys.TOTAL_WORLD_TIME) / TICKS_PER_SECOND / SECONDS_PER_MINUTE).timeFormat(
                        locale
                    )
                )
                model.addAttribute("overallPlayingTimeInMinutes", overallPlayingTime.timeFormat(locale))
                model.addAttribute("averagePlayingTimeInMinutes", averagePlayingTimeInMinutes.timeFormat(locale))
                model.addAttribute("playerMedals", playerService.getMedalsForPlayer(playername))
                model.addAttribute("killedMobsList", killedMobs)
                model.addAttribute("killedByMobsList", killedByMobs)
            }
        }

        model.addAttribute("runtime", runtimeInMilliseconds)

        return "player"
    }
}