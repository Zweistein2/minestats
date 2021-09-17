package de.zweistein2.minestats.services

import de.zweistein2.minestats.models.ServerBanModel
import de.zweistein2.minestats.repositories.ServerBanRepository
import de.zweistein2.minestats.repositories.entities.ServerBanEntity
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Service
class BanService(
    val serverBanRepository: ServerBanRepository,
) {
    companion object {
        fun ServerBanEntity.convertEntityToModel(): ServerBanModel = ServerBanModel(
            UUID.fromString(this.uuid), this.name,
            this.address, this.reason, LocalDateTime.ofInstant(Instant.ofEpochMilli(this.start), ZoneId.of("UTC")),
            LocalDateTime.ofInstant(Instant.ofEpochMilli(this.end), ZoneId.of("UTC")))
    }

    fun loadBans(): List<ServerBanModel> {
        val bans = serverBanRepository.findAll()

        return bans.stream().map { it.convertEntityToModel() }.toList() ?: listOf()
    }
}