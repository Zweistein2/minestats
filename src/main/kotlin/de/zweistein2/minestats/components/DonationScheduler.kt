package de.zweistein2.minestats.components

import de.zweistein2.minestats.repositories.DonationRepository
import de.zweistein2.minestats.repositories.entities.DonationEntity
import mu.KotlinLogging
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.sql.*
import java.util.*

@Component
class DonationScheduler(
    val donationRepository: DonationRepository,
    val minestatProperties: MinestatProperties
) {
    private val logger = KotlinLogging.logger {}

    @Scheduled(cron = "0 0 */3 * * *")
    private fun updateDonations() {
        if(minestatProperties.paypalDonations) {
            val conn: Connection
            val connectionProps = Properties()
            connectionProps["user"] = minestatProperties.paypalDbUser
            connectionProps["password"] = minestatProperties.paypalDbPassword

            val stmt: Statement
            var resultset: ResultSet

            try {
                Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance()
                conn = DriverManager.getConnection("jdbc:mysql://${minestatProperties.paypalDbUrl}/${minestatProperties.paypalDbName}", connectionProps)

                stmt = conn.createStatement()
                resultset = stmt.executeQuery("SELECT dateline, amount FROM aayush_ppdonate WHERE confirmed = 1;")

                if (stmt.execute("SELECT dateline, amount FROM aayush_ppdonate WHERE confirmed = 1;")) {
                    resultset = stmt.resultSet
                }

                var count = 0
                while (resultset.next()) {
                    val donationEntity = DonationEntity(null, resultset.getLong("dateline"), resultset.getFloat("amount"))

                    if(donationRepository.findByDatelineAndAmount(donationEntity.dateline, donationEntity.amount).isEmpty) {
                        donationRepository.save(donationEntity)
                        count++
                    }
                }

                logger.info { "Saved $count new donations" }
            } catch (ex: SQLException) {
                logger.error { ex }
            }
        }
    }
}