package de.zweistein2.minestats.repositories.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class ServerBanEntity(
    @Id
    val id: String? = null,
    val uuid: String,
    val name: String,
    val address: String,
    val reason: String,
    val start: Long,
    val end: Long,
)
