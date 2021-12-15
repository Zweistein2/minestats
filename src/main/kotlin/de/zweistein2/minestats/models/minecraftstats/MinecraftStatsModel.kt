package de.zweistein2.minestats.models.minecraftstats

import com.google.gson.JsonObject

open class MinecraftStatsModel(val stats: JsonObject) {
    fun forCategory(key: CategoryKeys): MinecraftStatsCategoryModel {
        return if(stats[key.jsonName] != null) {
            MinecraftStatsCategoryModel(stats[key.jsonName].asJsonObject, key)
        } else {
            MinecraftStatsCategoryModel(stats, key)
        }
    }
}