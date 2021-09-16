package de.zweistein2.minestats.repositories.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class ServerPlayerEntity(
    @Id
    val id: String? = null,
    val uuid: String,
    val username: String?,
    val changed: Long?,
    val stats_json: String,
)
