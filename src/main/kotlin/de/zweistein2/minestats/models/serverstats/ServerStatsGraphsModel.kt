package de.zweistein2.minestats.models.serverstats

data class ServerStatsGraphsModel(
    val players: String,
    val tps: String,
    val cpuLoad: String,
    val memoryLoad: Int,
    val modus: String,
    val donations: String,
)
