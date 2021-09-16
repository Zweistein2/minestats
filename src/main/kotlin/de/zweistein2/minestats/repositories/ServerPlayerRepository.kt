package de.zweistein2.minestats.repositories

import de.zweistein2.minestats.repositories.entities.ServerPlayerEntity
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface ServerPlayerRepository: MongoRepository<ServerPlayerEntity, String> {
    fun findByUsername(username: String): Optional<ServerPlayerEntity>
}