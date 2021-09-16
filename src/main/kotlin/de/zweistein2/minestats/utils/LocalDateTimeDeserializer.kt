package de.zweistein2.minestats.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import de.zweistein2.minestats.components.BitbucketProperties
import java.lang.reflect.Type
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class LocalDateTimeDeserializer(
    private val bitbucketProperties: BitbucketProperties,
): JsonDeserializer<LocalDateTime> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): LocalDateTime {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(json.asLong), ZoneId.of(bitbucketProperties.zoneId))
    }
}