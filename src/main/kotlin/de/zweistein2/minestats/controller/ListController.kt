package de.zweistein2.minestats.controller

import de.zweistein2.minestats.components.MinestatProperties
import de.zweistein2.minestats.components.PlayerStatComparator
import de.zweistein2.minestats.models.minecraftstats.*
import de.zweistein2.minestats.services.BanService
import de.zweistein2.minestats.services.PlayerService
import de.zweistein2.minestats.utils.StatAggregatorUtil
import de.zweistein2.torque.spring.MonitoringSpringWrapper
import java.util.*
import kotlin.system.measureTimeMillis
import org.springframework.cache.annotation.Cacheable
import org.springframework.context.MessageSource
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

private const val MAX_PLAYER_COUNT_FOR_LIST = 100

@Controller
class ListController(
    val playerService: PlayerService,
    val banService: BanService,
    val minestatProperties: MinestatProperties,
    val messageSource: MessageSource
) {
    val monitoring = MonitoringSpringWrapper(true).getMonitoring()

    @Cacheable("list")
    @GetMapping("/list")
    fun getList(
        model: Model, @RequestParam(defaultValue = "CUSTOM") statCategory: String = CategoryKeys.CUSTOM.name,
        @RequestParam(defaultValue = "JUMP") statName: String = CustomKeys.JUMP.name,
        @RequestParam(defaultValue = "false") isBlock: Boolean = false
    ): String {
        val runtimeInMilliseconds = measureTimeMillis {
            monitoring.withTimer(
                "showCompleteList",
                "frontend",
                Pair("statName", statName),
                Pair("statCategory", statCategory)
            ) {
                val key = MobKeys.valueOfOrNull(statName) ?: CustomKeys.valueOfOrNull(statName) ?: if (isBlock) {
                    BlockKeys.valueOfOrNull(statName)
                } else {
                    ItemKeys.valueOfOrNull(statName)
                } ?: CustomKeys.JUMP

                val bans = banService.loadBans()
                val players = playerService.loadAllPlayers()
                    .sortedWith(PlayerStatComparator(CategoryKeys.valueOf(statCategory), key)).take(
                        MAX_PLAYER_COUNT_FOR_LIST
                    )

                model.addAttribute("players", players.filter { player -> !bans.map { it.uuid }.contains(player.uuid) })
                model.addAttribute("statCategory", statCategory)
                model.addAttribute("statName", statName)
                model.addAttribute("isBlock", isBlock)
            }
        }

        model.addAttribute("runtime", runtimeInMilliseconds)

        return "list"
    }

    @Cacheable("bestlist")
    @GetMapping("/", "/bestlist")
    fun getBestlists(model: Model): String {
        val runtimeInMilliseconds = measureTimeMillis {
            monitoring.withTimer("showBestlists", "frontend") {
                val players = playerService.loadAllPlayers()
                val bans = banService.loadBans()

                val unbannedPlayers = players.filter { player -> !bans.map { it.uuid }.contains(player.uuid) }
                val totalMinedBlocksPerPlayer = StatAggregatorUtil.getBlockStatsPerPlayer(unbannedPlayers, CategoryKeys.MINED)
                val totalPlacedBlocksPerPlayer = StatAggregatorUtil.getBlockStatsPerPlayer(unbannedPlayers, CategoryKeys.USED)
                val totalPickedUpBlocksPerPlayer = StatAggregatorUtil.getBlockStatsPerPlayer(unbannedPlayers, CategoryKeys.PICKED_UP)
                val totalCraftedBlocksPerPlayer = StatAggregatorUtil.getBlockStatsPerPlayer(unbannedPlayers, CategoryKeys.CRAFTED)
                val totalDroppedBlocksPerPlayer = StatAggregatorUtil.getBlockStatsPerPlayer(unbannedPlayers, CategoryKeys.DROPPED)

                val totalBrokenItemsPerPlayer = StatAggregatorUtil.getItemStatsPerPlayer(unbannedPlayers, CategoryKeys.BROKEN)
                val totalPickedUpItemsPerPlayer = StatAggregatorUtil.getItemStatsPerPlayer(unbannedPlayers, CategoryKeys.PICKED_UP)
                val totalDroppedItemsPerPlayer = StatAggregatorUtil.getItemStatsPerPlayer(unbannedPlayers, CategoryKeys.DROPPED)
                val totalCraftedItemsPerPlayer = StatAggregatorUtil.getItemStatsPerPlayer(unbannedPlayers, CategoryKeys.CRAFTED)
                val totalUsedItemsPerPlayer = StatAggregatorUtil.getItemStatsPerPlayer(unbannedPlayers, CategoryKeys.USED)

                model.addAttribute("players", unbannedPlayers)
                model.addAttribute("totalMinedBlocksPerPlayer", totalMinedBlocksPerPlayer)
                model.addAttribute("totalPlacedBlocksPerPlayer", totalPlacedBlocksPerPlayer)
                model.addAttribute("totalPickedUpBlocksPerPlayer", totalPickedUpBlocksPerPlayer)
                model.addAttribute("totalCraftedBlocksPerPlayer", totalCraftedBlocksPerPlayer)
                model.addAttribute("totalDroppedBlocksPerPlayer", totalDroppedBlocksPerPlayer)

                model.addAttribute("totalBrokenItemsPerPlayer", totalBrokenItemsPerPlayer)
                model.addAttribute("totalPickedUpItemsPerPlayer", totalPickedUpItemsPerPlayer)
                model.addAttribute("totalDroppedItemsPerPlayer", totalDroppedItemsPerPlayer)
                model.addAttribute("totalCraftedItemsPerPlayer", totalCraftedItemsPerPlayer)
                model.addAttribute("totalUsedItemsPerPlayer", totalUsedItemsPerPlayer)
            }
        }

        model.addAttribute("runtime", runtimeInMilliseconds)

        return "bestlist"
    }

    @Cacheable("blocks")
    @GetMapping("/blocks")
    fun getBlocklists(model: Model, @RequestParam("name") blockName: String?): String {
        val runtimeInMilliseconds = measureTimeMillis {
            monitoring.withTimer("showBlockList", "frontend", Pair("blockName", blockName ?: "")) {
                val players = playerService.loadAllPlayers()
                val bans = banService.loadBans()
                val tags = BlockKeys.getAllTags().toList().distinct().sortedBy { it.name }
                val blockKeys = BlockKeys.values()

                model.addAttribute("players", players.filter { player -> !bans.map { it.uuid }.contains(player.uuid) })
                model.addAttribute("blockName", blockName)
                model.addAttribute("tags", tags)
                model.addAttribute("blockKeys", blockKeys)
            }
        }

        model.addAttribute("runtime", runtimeInMilliseconds)

        return "blocks"
    }

    @GetMapping("/blocksearch")
    fun getBlocklistsFiltered(
        model: Model,
        @RequestParam("name") blockName: String,
        locale: Locale = Locale.forLanguageTag(minestatProperties.locale)
    ): String {
        val runtimeInMilliseconds = measureTimeMillis {
            monitoring.withTimer("showBlockListFiltered", "frontend", Pair("blockName", blockName)) {
                val players = playerService.loadAllPlayers()
                val bans = banService.loadBans()
                val blockKeys = BlockKeys.values()
                    .filter { messageSource.getMessage(it.name, null, locale).contains(blockName, true) }
                val tags = blockKeys.stream().flatMap { it.tags.stream() }.toList().distinct().sortedBy { it.name }

                model.addAttribute("players", players.filter { player -> !bans.map { it.uuid }.contains(player.uuid) })
                model.addAttribute("blockName", blockName)
                model.addAttribute("tags", tags)
                model.addAttribute("blockKeys", blockKeys)
            }
        }

        model.addAttribute("runtime", runtimeInMilliseconds)

        return "fragments/blocks/blocklist"
    }

    @Cacheable("items")
    @GetMapping("/items")
    fun getItemlists(model: Model, @RequestParam("name") itemName: String?): String {
        val runtimeInMilliseconds = measureTimeMillis {
            monitoring.withTimer("showItemList", "frontend", Pair("itemName", itemName ?: "")) {
                val players = playerService.loadAllPlayers()
                val bans = banService.loadBans()

                model.addAttribute("players", players.filter { player -> !bans.map { it.uuid }.contains(player.uuid) })
                model.addAttribute("itemName", itemName)
            }
        }

        model.addAttribute("runtime", runtimeInMilliseconds)

        return "items"
    }

    @Cacheable("mobs")
    @GetMapping("/mobs")
    fun getMoblists(model: Model, @RequestParam("name") mobName: String?): String {
        val runtimeInMilliseconds = measureTimeMillis {
            monitoring.withTimer("showMobList", "frontend", Pair("mobName", mobName ?: "")) {
                val players = playerService.loadAllPlayers()
                val bans = banService.loadBans()

                model.addAttribute("players", players.filter { player -> !bans.map { it.uuid }.contains(player.uuid) })
                model.addAttribute("mobName", mobName)
            }
        }

        model.addAttribute("runtime", runtimeInMilliseconds)

        return "mobs"
    }
}