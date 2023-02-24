package de.zweistein2.minestats.utils

import de.zweistein2.minestats.models.ServerPlayerModel

object MedalAggregatorUtil {
    fun accountMedalsForTopThreePlayers(players: List<ServerPlayerModel>, allMedals: MutableMap<String, Triple<Int, Int, Int>>) {
        players.forEachIndexed { index, player ->
            val medals = allMedals.getOrPut(player.username) { Triple(0, 0, 0) }

            val newMedals = when (index) {
                0 -> {
                    Triple(medals.first + 1, medals.second, medals.third)
                }
                1 -> {
                    Triple(medals.first, medals.second + 1, medals.third)
                }
                2 -> {
                    Triple(medals.first, medals.second, medals.third + 1)
                }

                else -> medals
            }

            allMedals[player.username] = newMedals
        }
    }
}