package de.zweistein2.minestats.repositories

import de.zweistein2.minestats.repositories.entities.ServerPlayerHistoricEntity
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface ServerPlayerHistoricRepository: MongoRepository<ServerPlayerHistoricEntity, String> {
    fun findByUsername(username: String): Optional<ServerPlayerHistoricEntity>
}