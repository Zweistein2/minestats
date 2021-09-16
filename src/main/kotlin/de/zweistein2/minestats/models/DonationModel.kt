package de.zweistein2.minestats.models

import java.time.LocalDate

data class DonationModel(
    val timestamp: LocalDate,
    val amount: Float,
)
