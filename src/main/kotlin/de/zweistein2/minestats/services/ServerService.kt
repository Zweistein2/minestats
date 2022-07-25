package de.zweistein2.minestats.services

import com.google.gson.Gson
import de.zweistein2.minestats.components.MinestatProperties
import de.zweistein2.minestats.models.DonationModel
import de.zweistein2.minestats.models.ServerStatsModel
import de.zweistein2.minestats.repositories.DonationRepository
import de.zweistein2.minestats.repositories.ServerStatsRepository
import de.zweistein2.minestats.repositories.entities.DonationEntity
import de.zweistein2.minestats.repositories.entities.ServerStatsEntity
import de.zweistein2.minestats.utils.DateProgression.Companion.downTo
import de.zweistein2.minestats.utils.DateProgression.Companion.limit
import de.zweistein2.minestats.utils.DateProgression.Companion.unit
import de.zweistein2.minestats.utils.TimeUnit
import de.zweistein2.torque.spring.MonitoringSpringWrapper
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDate
import java.time.Month
import java.time.ZoneId
import java.util.*

@Service
class ServerService(
    val serverStatsRepository: ServerStatsRepository,
    val donationRepository: DonationRepository,
    val minestatProperties: MinestatProperties,
) {
    val monitoring = MonitoringSpringWrapper(true).getMonitoring()

    companion object {
        fun DonationEntity.convertEntityToModel(): DonationModel = DonationModel(LocalDate.ofInstant(Instant.ofEpochSecond(dateline), ZoneId.of("GMT+2")), this.amount)
        fun ServerStatsEntity.convertEntityToModel(): ServerStatsModel = ServerStatsModel(this.timestamp, this.players ?: 0, this.ram ?: 0, this.cpu ?: 0,
            this.tps ?: 0.0f, this.worldsize ?: 0, this.properties?.toProperties() ?: Properties())

        fun String.toProperties(): Properties {
            val lines = this.lines().filter { it.isNotBlank() && !it.trim().startsWith("#") }.toList()
            val properties = Properties()

            for(line in lines) {
                val keyValuePair = line.split("=")

                properties[keyValuePair[0]] = keyValuePair[1]
            }

            return properties
        }
    }

    fun getDonationsPerMonth(): MutableMap<LocalDate, Float> {
        return monitoring.withTimer("getDonationsPerMonth", "database") {
            val donations = donationRepository.findAll().map { it.convertEntityToModel() }
            val donationsPerMonth = mutableMapOf<LocalDate, Float>()

            for (date in LocalDate.of(LocalDate.now().year, LocalDate.now().month, 1) downTo LocalDate.of(
                2015,
                Month.NOVEMBER,
                1
            ) unit TimeUnit.MONTHS limit -1L) {
                val donationForMonth =
                    donations.stream().filter { it.timestamp.year == date.year && it.timestamp.month == date.month }
                        .map { it.amount }.reduce { t, u -> t + u }

                donationsPerMonth[date] = donationForMonth.orElse(0.0f) + minestatProperties.monthlyDonations
            }

            donationsPerMonth
        }
    }

    fun getChartPayload(title: String, legendLabel: String, axisLabel: String, yMin: Number, yMax: Number, labels: List<String>, Data: List<Number>): String {
        return monitoring.withTimer("getChartPayload", "database", Pair("chart", title)) {
            val payload = ChartPayload(title, legendLabel, axisLabel, yMin, yMax, labels, Data)

            Gson().toJson(payload)
        }
    }

    fun loadHistory(): List<ServerStatsModel> {
        return monitoring.withTimer("loadHistory", "database") {
            val result = serverStatsRepository.findAll()

            if (result.isNotEmpty()) result.stream().map { it.convertEntityToModel() }.toList() else listOf()
        }
    }
}