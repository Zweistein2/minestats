package de.zweistein2.minestats.models

import java.time.LocalDateTime
import java.util.*

data class ServerStatsModel(
    val timestamp: LocalDateTime,
    val players: Int,
    val ram: Int,
    val cpu: Int,
    val tps: Float,
    val worldsize: Long,
    val properties: Properties
)
