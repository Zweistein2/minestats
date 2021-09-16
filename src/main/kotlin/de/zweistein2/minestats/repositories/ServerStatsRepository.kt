package de.zweistein2.minestats.repositories

import de.zweistein2.minestats.repositories.entities.ServerStatsEntity
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface ServerStatsRepository: MongoRepository<ServerStatsEntity, String> {
}