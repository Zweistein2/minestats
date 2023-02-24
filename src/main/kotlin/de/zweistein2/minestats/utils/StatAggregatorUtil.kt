package de.zweistein2.minestats.utils

import de.zweistein2.minestats.models.ServerPlayerModel
import de.zweistein2.minestats.models.minecraftstats.BlockKeys
import de.zweistein2.minestats.models.minecraftstats.CategoryKeys
import de.zweistein2.minestats.models.minecraftstats.ItemKeys

object StatAggregatorUtil {
    fun getBlockStatsPerPlayer(players: List<ServerPlayerModel>, category: CategoryKeys, limit: Int = 10): Map<ServerPlayerModel, Long> {
        val totalBlocksPerPlayer = mutableMapOf<ServerPlayerModel, Long>()

        players.forEach { player ->
            totalBlocksPerPlayer[player] = 0L

            BlockKeys.values().forEach { blockKey ->
                totalBlocksPerPlayer[player] = totalBlocksPerPlayer[player]!! + player.stats.forCategory(category).forStat(blockKey)
            }
        }

        return totalBlocksPerPlayer.toList().sortedByDescending { (_, value) -> value }.take(limit).toMap()
    }

    fun getBlockStatsForPlayer(player: ServerPlayerModel, category: CategoryKeys, limit: Int = 5): Map<BlockKeys, Long> {
        val blocks = mutableMapOf<BlockKeys, Long>()

        BlockKeys.values().forEach { blockKey ->
            blocks[blockKey] = player.stats.forCategory(category).forStat(blockKey)
        }

        return blocks.toList().sortedByDescending { (_, value) -> value }.take(limit).toMap()
    }

    fun getTotalBlockStatsForPlayer(player: ServerPlayerModel, category: CategoryKeys): Long {
        var totalBlocks = 0L

        BlockKeys.values().forEach { blockKey ->
            totalBlocks += player.stats.forCategory(category).forStat(blockKey)
        }

        return totalBlocks
    }

    fun getItemStatsPerPlayer(players: List<ServerPlayerModel>, category: CategoryKeys, limit: Int = 10): Map<ServerPlayerModel, Long> {
        val totalItemsPerPlayer = mutableMapOf<ServerPlayerModel, Long>()

        players.forEach { player ->
            totalItemsPerPlayer[player] = 0L

            ItemKeys.values().forEach { itemKey ->
                totalItemsPerPlayer[player] = totalItemsPerPlayer[player]!! + player.stats.forCategory(category).forStat(itemKey)
            }
        }

        return totalItemsPerPlayer.toList().sortedByDescending { (_, value) -> value }.take(limit).toMap()
    }

    fun getItemStatsForPlayer(player: ServerPlayerModel, category: CategoryKeys, limit: Int = 5): Map<ItemKeys, Long> {
        val items = mutableMapOf<ItemKeys, Long>()

        ItemKeys.values().forEach { itemKey ->
            items[itemKey] = player.stats.forCategory(category).forStat(itemKey)
        }

        return items.toList().sortedByDescending { (_, value) -> value }.take(limit).toMap()
    }

    fun getTotalItemStatsForPlayer(player: ServerPlayerModel, category: CategoryKeys): Long {
        var totalItems = 0L

        ItemKeys.values().forEach { itemKey ->
            totalItems += player.stats.forCategory(category).forStat(itemKey)
        }

        return totalItems
    }
}