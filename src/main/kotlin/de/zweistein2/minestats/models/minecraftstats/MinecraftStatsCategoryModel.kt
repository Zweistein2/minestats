package de.zweistein2.minestats.models.minecraftstats

import com.google.gson.JsonObject

class MinecraftStatsCategoryModel(val stats: JsonObject, val category: CategoryKeys) {
    fun forStat(keyName: String, isBlock: Boolean): Long {
        val key = CustomKeys.valueOfOrNull(keyName) ?: MobKeys.valueOfOrNull(keyName) ?: if (isBlock) {
            BlockKeys.valueOfOrNull(keyName)
        } else {
            ItemKeys.valueOfOrNull(keyName)
        } ?: throw IllegalArgumentException("the specified keyname: \"$keyName\" doesn't exist")

        return forStat(key)
    }

    fun forStat(key: Key): Long {
        when (key) {
            is BlockKeys -> {
                require(
                    category in listOf(
                        CategoryKeys.MINED,
                        CategoryKeys.PICKED_UP,
                        CategoryKeys.DROPPED,
                        CategoryKeys.CRAFTED,
                        CategoryKeys.USED
                    )
                )
                { "Block stat keys can only be called in the mined, picked_up, dropped, crafted and used stat categories" }
            }
            is ItemKeys -> {
                require(
                    category in listOf(
                        CategoryKeys.BROKEN,
                        CategoryKeys.PICKED_UP,
                        CategoryKeys.DROPPED,
                        CategoryKeys.CRAFTED,
                        CategoryKeys.USED
                    )
                )
                { "Item stat keys can only be called in the broken, picked_up, dropped, crafted and used stat categories" }
            }
            is CustomKeys -> {
                require(category in listOf(CategoryKeys.CUSTOM))
                { "Custom stat keys can only be called in the custom stat category" }
            }
            is MobKeys -> {
                require(category in listOf(CategoryKeys.KILLED, CategoryKeys.KILLED_BY))
                { "Mob stat keys can only be called in the killed and killed_by stat category" }
            }
            else -> require(false)
            { "Key isn't a valid stat key" }
        }

        return if (stats[key.jsonName] != null) {
            stats[key.jsonName].asLong
        } else if (stats[key.alternatives.find { stats[it] != null }] != null) {
            stats[key.alternatives.find { stats[it] != null }].asLong
        } else if (stats["${category.alternative}${key.alternatives.find { stats["${category.alternative}$it"] != null }}"] != null) {
            stats["${category.alternative}${key.alternatives.find { stats["${category.alternative}$it"] != null }}"].asLong
        } else {
            0L
        }
    }
}