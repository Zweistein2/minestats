package de.zweistein2.minestats.repositories.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class DonationEntity(
    @Id
    val id: String? = null,
    val dateline: Long,
    val amount: Float,
)
