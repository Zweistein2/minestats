package de.zweistein2.minestats.models

import java.time.LocalDateTime
import java.util.*

data class ServerBanModel(
    val uuid: UUID,
    val name: String,
    val address: String,
    val reason: String,
    val start: LocalDateTime,
    val end: LocalDateTime,
)
