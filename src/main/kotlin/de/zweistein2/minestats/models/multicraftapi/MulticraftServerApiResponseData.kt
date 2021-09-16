package de.zweistein2.minestats.models.multicraftapi

import com.google.gson.annotations.SerializedName

data class MulticraftServerApiResponseData(
    @SerializedName("Server")
    val server: MulticraftServerApiResponseServerData?,
    val cpu: Float?,
    val memory: Float?,
    val quota: Int?,
    val status: String?,
    val onlinePlayers: Int?,
    val maxPlayers: Int?,
)
