package de.zweistein2.minestats.components

import de.zweistein2.minestats.models.ServerPlayerModel
import de.zweistein2.minestats.models.minecraftstats.*

class PlayerStatComparator(private val statCategory: CategoryKeys, private val statName: Key): Comparator<ServerPlayerModel> {
    companion object {
        @JvmStatic
        fun of(statCategory: CategoryKeys, statName: Key): PlayerStatComparator {
            return PlayerStatComparator(statCategory, statName)
        }
    }

    override fun compare(playerOne: ServerPlayerModel, playerTwo: ServerPlayerModel): Int {
        return when {
            playerOne.stats.forCategory(statCategory).forStat(statName) == playerTwo.stats.forCategory(statCategory).forStat(statName) -> { 0 }
            playerOne.stats.forCategory(statCategory).forStat(statName) > playerTwo.stats.forCategory(statCategory).forStat(statName) -> { -1 }
            playerOne.stats.forCategory(statCategory).forStat(statName) < playerTwo.stats.forCategory(statCategory).forStat(statName) -> { 1 }
            else -> 0
        }
    }
}