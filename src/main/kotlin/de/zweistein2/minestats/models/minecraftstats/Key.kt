package de.zweistein2.minestats.models.minecraftstats

interface Key {
    val jsonName: String
    val alternatives: Array<out String>
}