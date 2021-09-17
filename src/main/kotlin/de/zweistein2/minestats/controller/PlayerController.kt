package de.zweistein2.minestats.controller

import de.zweistein2.minestats.components.MinestatProperties
import de.zweistein2.minestats.models.minecraftstats.CategoryKeys
import de.zweistein2.minestats.models.minecraftstats.CustomKeys
import de.zweistein2.minestats.models.minecraftstats.MobKeys
import de.zweistein2.minestats.services.PlayerService
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.time.*
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.system.measureTimeMillis

@Controller
@CacheConfig(cacheNames = ["playerStats"])
class PlayerController(
    val playerService: PlayerService,
    val minestatProperties: MinestatProperties
) {
    companion object {
        @JvmStatic
        fun Long.timeFormat(locale: Locale): String {
            val bundle = ResourceBundle.getBundle("lang/messages", locale)
            val duration = Duration.ofMinutes(this)

            return "${duration.toDaysPart()} ${bundle.getString("days")}, ${duration.toHoursPart()} ${bundle.getString("hours")} ${bundle.getString("and")} ${duration.toMinutesPart()} ${bundle.getString("minutes")}"
        }

        @JvmStatic
        fun LocalDateTime.timeFormat(locale: Locale): String {
            val bundle = ResourceBundle.getBundle("lang/messages", locale)
            val duration = Duration.between(this, LocalDateTime.now(ZoneId.of("UTC")))

            return "${duration.toDaysPart()} ${bundle.getString("days")}, ${duration.toHoursPart()} ${bundle.getString("hours")} ${bundle.getString("and")} ${duration.toMinutesPart()} ${bundle.getString("minutes")}"
        }
    }

    @Cacheable
    @GetMapping("/player")
    fun getPlayerStats(model: Model, @RequestParam playername: String?, locale: Locale = Locale.forLanguageTag(minestatProperties.locale)): String {
        val runtimeInMilliseconds = measureTimeMillis {
            val players = playerService.loadPlayers()

            if(playername.isNullOrBlank()) {
                val playerCount = players.size
                val playersOnline = playerService.loadOnlinePlayers().size
                val playersOnlineThirtyDaysCount = players.filter { it.changed != null && it.changed.isAfter(LocalDateTime.now(ZoneId.of("UTC")).minusDays(30)) }.size
                val twenthyLastOnlinePlayers = players.sortedByDescending { it.changed }.take(20)
                    .associate { it.username to ChronoUnit.MINUTES.between(LocalDateTime.now(ZoneId.of("UTC")), it.changed).times(-1L).timeFormat(locale) }

                // TotalTimeInTicks / 20 Ticks/Sec / 60 Sec/Min / CountLeaveGame
                var averagePlayingTimeInMinutes = 0L
                for (player in players) {
                    val leftGames = if(player.stats.forCategory(CategoryKeys.CUSTOM).forStat(CustomKeys.LEAVE_GAME) == 0L) 1L else player.stats.forCategory(CategoryKeys.CUSTOM).forStat(CustomKeys.LEAVE_GAME)

                    averagePlayingTimeInMinutes += player.stats.forCategory(CategoryKeys.CUSTOM).forStat(CustomKeys.TOTAL_WORLD_TIME) / 20 / 60 / leftGames
                }
                averagePlayingTimeInMinutes /= players.size

                model.addAttribute("playersOnline", playersOnline)
                model.addAttribute("playerCount", playerCount)
                model.addAttribute("playersOnlineThirtyDaysCount", playersOnlineThirtyDaysCount)
                model.addAttribute("averagePlayingTimeInMinutes", averagePlayingTimeInMinutes.timeFormat(locale))
                model.addAttribute("twenthyLastOnlinePlayers", twenthyLastOnlinePlayers)
                model.addAttribute("topTenPlayers", playerService.getTopTenPlayers())
            } else {
                require(players.any { it.username == playername }) { "Der Benutzer mit dem Namen $playername existiert nicht"}

                val player = players.first { it.username == playername }

                val leftGames = if(player.stats.forCategory(CategoryKeys.CUSTOM).forStat(CustomKeys.LEAVE_GAME) == 0L) 1L else player.stats.forCategory(CategoryKeys.CUSTOM).forStat(CustomKeys.LEAVE_GAME)
                // TotalTimeInTicks / 20 Ticks/Sec / 60 Sec/Min / CountLeaveGame
                val averagePlayingTimeInMinutes = player.stats.forCategory(CategoryKeys.CUSTOM).forStat(CustomKeys.TOTAL_WORLD_TIME) / 20 / 60 / leftGames

                val killedMobs = mutableListOf<Pair<String, Long>>()
                for (mobKey in MobKeys.values()) {
                    killedMobs.add(mobKey.name to player.stats.forCategory(CategoryKeys.KILLED).forStat(mobKey))
                }
                killedMobs.removeIf { it.second == 0L }
                killedMobs.sortByDescending { it.second }

                val killedByMobs = mutableListOf<Pair<String, Long>>()
                for (mobKey in MobKeys.values()) {
                    killedByMobs.add(mobKey.name to player.stats.forCategory(CategoryKeys.KILLED_BY).forStat(mobKey))
                }
                killedByMobs.removeIf { it.second == 0L }
                killedByMobs.sortByDescending { it.second }

                model.addAttribute("player", player)
                model.addAttribute("lastOnlineInMinutes", player.changed?.timeFormat(locale) ?: LocalDateTime.now(ZoneId.of("UTC")).timeFormat(locale))
                model.addAttribute("playingTimeInMinutes", (player.stats.forCategory(CategoryKeys.CUSTOM).forStat(CustomKeys.TOTAL_WORLD_TIME) / 20 / 60).timeFormat(locale))
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