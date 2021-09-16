package de.zweistein2.minestats.models.serverstats

data class ServerStatsMetaModel(
    val onlinePlayers: Int,
    val worldSize: Float,
    val currentMemoryLoad: Int,
    val maxMemoryLoad: Int,
    val tps: Float,
    val cpuLoad: Float,
)
