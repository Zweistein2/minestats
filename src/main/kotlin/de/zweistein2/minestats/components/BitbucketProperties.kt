package de.zweistein2.minestats.components

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class BitbucketProperties(
    @Value("\${minestats.bitbucket.apiUrl}")
    var apiUrl: String,
    @Value("\${minestats.bitbucket.apiToken}")
    var apiToken: String,
    @Value("\${minestats.bitbucket.zoneId}")
    var zoneId: String,
)