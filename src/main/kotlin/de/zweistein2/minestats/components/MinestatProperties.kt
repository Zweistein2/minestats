package de.zweistein2.minestats.components

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class MinestatProperties(
    @Value("\${minestats.locale}")
    var locale: String,
    @Value("\${minestats.queryIp}")
    var queryIp: String,
    @Value("\${minestats.queryPort}")
    var queryPort: Int,
    @Value("\${minestats.aayush.paypaldonate}")
    var paypalDonations: Boolean,
    @Value("\${minestats.aayush.db.user}")
    var paypalDbUser: String,
    @Value("\${minestats.aayush.db.password}")
    var paypalDbPassword: String,
    @Value("\${minestats.aayush.db.url}")
    var paypalDbUrl: String,
    @Value("\${minestats.aayush.db.databaseName}")
    var paypalDbName: String,
    @Value("\${minestats.monthlyDonations}")
    var monthlyDonations: Int,
    @Value("\${minestats.corecount}")
    var coreCount: Int,
    @Value("\${minestats.historyDays}")
    var historyDays: Long,
    @Value("\${minestats.hideSeed}")
    var hideSeed: Boolean,
)