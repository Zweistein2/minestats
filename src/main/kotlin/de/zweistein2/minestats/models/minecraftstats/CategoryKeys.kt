package de.zweistein2.minestats.models.minecraftstats

enum class CategoryKeys(val jsonName: String) {
    BROKEN("minecraft:broken"),
    DROPPED("minecraft:dropped"),
    KILLED("minecraft:killed"),
    PICKED_UP("minecraft:picked_up"),
    MINED("minecraft:mined"),
    USED("minecraft:used"),
    CUSTOM("minecraft:custom"),
    KILLED_BY("minecraft:killed_by"),
    CRAFTED("minecraft:crafted"),
}