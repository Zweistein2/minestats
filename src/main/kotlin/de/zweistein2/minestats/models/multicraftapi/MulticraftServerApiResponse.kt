package de.zweistein2.minestats.models.multicraftapi

data class MulticraftServerApiResponse(
    val success: Boolean,
    val errors: List<String>,
    val data: MulticraftServerApiResponseData
)
