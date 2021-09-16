package de.zweistein2.minestats.repositories

import de.zweistein2.minestats.repositories.entities.DonationEntity
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface DonationRepository: MongoRepository<DonationEntity, String> {
    fun findByDatelineAndAmount(dateline: Long, amount: Float): Optional<DonationEntity>
}