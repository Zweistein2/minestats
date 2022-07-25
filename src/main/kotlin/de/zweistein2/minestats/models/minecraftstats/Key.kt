package de.zweistein2.minestats.models.minecraftstats

interface Key {
    val jsonName: String
    val show: Boolean
    val tags: List<Tags>
    val alternatives: Array<out String>
}