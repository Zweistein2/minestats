package de.zweistein2.minestats.models.serverstats

data class ServerStatsInfoModel(
    val name: String,
    val version: String,
    val software: String,
    val maxPlayers: Int,
    val currentPlayers: List<String>,
    val modus: String,
    val map: String,
    val seed: String,
    val difficulty: String,
    val mobs: Boolean,
    val npcs: Boolean,
    val animals: Boolean,
    val viewDistance: Int,
    val pvp: Boolean,
    val whitelist: Boolean,
)
