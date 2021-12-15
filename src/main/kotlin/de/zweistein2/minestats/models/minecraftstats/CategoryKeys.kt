package de.zweistein2.minestats.models.minecraftstats

enum class CategoryKeys(val jsonName: String, val alternative: String) {
    BROKEN("minecraft:broken", "stat_breakItem_"),
    DROPPED("minecraft:dropped", "UNUSED"),
    KILLED("minecraft:killed", "stat_killEntity_"),
    PICKED_UP("minecraft:picked_up", "UNUSED"),
    MINED("minecraft:mined", "stat_mineBlock_"),
    USED("minecraft:used", "stat_useItem_"),
    CUSTOM("minecraft:custom", "stat_"),
    KILLED_BY("minecraft:killed_by", "stat_entityKilledBy_"),
    CRAFTED("minecraft:crafted", "stat_craftItem_"),
}