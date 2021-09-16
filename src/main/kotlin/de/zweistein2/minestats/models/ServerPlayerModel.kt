package de.zweistein2.minestats.models

import de.zweistein2.minestats.models.minecraftstats.MinecraftStatsModel
import java.time.LocalDateTime
import java.util.*

data class ServerPlayerModel(
    val uuid: UUID,
    val username: String,
    val changed: LocalDateTime?,
    val stats: MinecraftStatsModel,
)
