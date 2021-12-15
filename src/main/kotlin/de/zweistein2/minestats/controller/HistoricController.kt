package de.zweistein2.minestats.controller

import de.zweistein2.minestats.components.MinestatProperties
import de.zweistein2.minestats.controller.PlayerController.Companion.timeFormat
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
import java.util.*
import kotlin.system.measureTimeMillis

@Controller
@CacheConfig(cacheNames = ["historic"])
class HistoricController(
    val playerService: PlayerService,
    val minestatProperties: MinestatProperties
) {
    @Cacheable
    @GetMapping("/historic")
    fun getHistoricPlayerStats(model: Model, @RequestParam playername: String?, locale: Locale = Locale.forLanguageTag(minestatProperties.locale)): String {
        val runtimeInMilliseconds = measureTimeMillis {
            if(playername.isNullOrBlank()) {
                val players = playerService.loadUnbannedHistoricPlayers()

                model.addAttribute("players", players)
            } else {
                require(!playername.isNullOrBlank()) { "Es muss ein Benutzername angegeben werden!"}
                val player = playerService.loadHistoricPlayer(playername, true)!!

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
                model.addAttribute("playingTimeInMinutes", (player.stats.forCategory(CategoryKeys.CUSTOM).forStat(CustomKeys.TOTAL_WORLD_TIME) / 20 / 60).timeFormat(locale))
                model.addAttribute("averagePlayingTimeInMinutes", averagePlayingTimeInMinutes.timeFormat(locale))
                model.addAttribute("killedMobsList", killedMobs)
                model.addAttribute("killedByMobsList", killedByMobs)
            }
        }

        model.addAttribute("runtime", runtimeInMilliseconds)

        return "historic"
    }
}