package de.zweistein2.minestats.components

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class MulticraftProperties(
    @Value("\${minestats.multicraft.apiUrl}")
    var apiUrl: String,
    @Value("\${minestats.multicraft.user}")
    var user: String,
    @Value("\${minestats.multicraft.key}")
    var key: String,
    @Value("\${minestats.multicraft.serverId}")
    var serverId: String,
)
