package de.zweistein2.minestats.controller

import de.zweistein2.minestats.components.PlayerStatComparator
import de.zweistein2.minestats.models.minecraftstats.*
import de.zweistein2.minestats.services.PlayerService
import mu.KotlinLogging
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import kotlin.system.measureTimeMillis

@Controller
class ListController(
    val playerService: PlayerService,
) {
    @Cacheable("list")
    @GetMapping("/list")
    fun getList(model: Model, @RequestParam(defaultValue = "CUSTOM") statCategory: String = CategoryKeys.CUSTOM.name,
                @RequestParam(defaultValue = "JUMP") statName: String = CustomKeys.JUMP.name,
                @RequestParam(defaultValue = "false") isBlock: Boolean = false): String {
        val runtimeInMilliseconds = measureTimeMillis {
            val key = MobKeys.valueOfOrNull(statName) ?: CustomKeys.valueOfOrNull(statName) ?: if(isBlock) { BlockKeys.valueOfOrNull(statName) } else { ItemKeys.valueOfOrNull(statName) } ?: CustomKeys.JUMP

            val players = playerService.loadPlayers().sortedWith(PlayerStatComparator(CategoryKeys.valueOf(statCategory), key)).take(100)

            model.addAttribute("players", players)
            model.addAttribute("statCategory", statCategory)
            model.addAttribute("statName", statName)
            model.addAttribute("isBlock", isBlock)
        }

        model.addAttribute("runtime", runtimeInMilliseconds)

        return "list"
    }

    @Cacheable("bestlist")
    @GetMapping("/", "/bestlist")
    fun getBestlists(model: Model): String {
        val runtimeInMilliseconds = measureTimeMillis {
            val players = playerService.loadPlayers()

            model.addAttribute("players", players)
        }

        model.addAttribute("runtime", runtimeInMilliseconds)

        return "bestlist"
    }

    @Cacheable("blocks")
    @GetMapping("/blocks")
    fun getBlocklists(model: Model, @RequestParam("name") blockName: String?): String {
        val runtimeInMilliseconds = measureTimeMillis {
            val players = playerService.loadPlayers()

            model.addAttribute("players", players)
            model.addAttribute("blockName", blockName)
        }

        model.addAttribute("runtime", runtimeInMilliseconds)

        return "blocks"
    }

    @Cacheable("items")
    @GetMapping("/items")
    fun getItemlists(model: Model, @RequestParam("name") itemName: String?): String {
        val runtimeInMilliseconds = measureTimeMillis {
            val players = playerService.loadPlayers()

            model.addAttribute("players", players)
            model.addAttribute("itemName", itemName)
        }

        model.addAttribute("runtime", runtimeInMilliseconds)

        return "items"
    }

    @Cacheable("mobs")
    @GetMapping("/mobs")
    fun getMoblists(model: Model, @RequestParam("name") mobName: String?): String {
        val runtimeInMilliseconds = measureTimeMillis {
            val players = playerService.loadPlayers()

            model.addAttribute("players", players)
            model.addAttribute("mobName", mobName)
        }

        model.addAttribute("runtime", runtimeInMilliseconds)

        return "mobs"
    }
}