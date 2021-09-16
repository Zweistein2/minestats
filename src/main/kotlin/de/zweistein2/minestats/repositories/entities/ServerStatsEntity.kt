package de.zweistein2.minestats.repositories.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class ServerStatsEntity(
    @Id
    val id: String? = null,
    val timestamp: LocalDateTime,
    val players: Int?,
    val ram: Int?,
    val cpu: Int?,
    val tps: Float?,
    val worldsize: Long?,
    val properties: String?,
)
