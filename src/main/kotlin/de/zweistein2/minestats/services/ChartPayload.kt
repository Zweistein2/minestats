package de.zweistein2.minestats.services

data class ChartPayload(
    val title: String,
    val legendLabel: String,
    val axisLabel: String,
    val yMin: Number,
    val yMax: Number,
    val labels: List<String>,
    val data: List<Number>
)
