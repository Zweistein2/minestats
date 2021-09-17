package de.zweistein2.minestats.repositories

import de.zweistein2.minestats.repositories.entities.ServerBanEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface ServerBanRepository: MongoRepository<ServerBanEntity, String> {
}