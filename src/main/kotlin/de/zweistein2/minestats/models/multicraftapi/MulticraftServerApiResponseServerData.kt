package de.zweistein2.minestats.models.multicraftapi

import com.google.gson.annotations.SerializedName

data class MulticraftServerApiResponseServerData(
    @SerializedName("memory")
    val maxMemory: Int,
    val startMemory: Int,
    val port: Int,
    val id: Int,
    val name: String,
    val ip: String,
    val dir: String,
    val world: String,
    val players: Int,
    val jarfile: String,
    @SerializedName("prev_jarfile")
    val prevJarfile: String,
    val jardir: String,
    val template: String,
    val setup: String,
    val autostart: Boolean,
    @SerializedName("default_level")
    val defaultLevel: Int,
    @SerializedName("daemon_id")
    val daemonId: Int,
    @SerializedName("announce_save")
    val announceSave: Boolean,
    @SerializedName("kick_delay")
    val kickDelay: Int,
    val suspended: Boolean,
    val autosave: Boolean,
    val params: String,
    @SerializedName("crash_check")
    val crashCheck: String,
    @SerializedName("disk_quota")
    val diskQuota: Int,
    val domain: String,
)
