package de.zweistein2.minestats.services

import com.google.gson.GsonBuilder
import de.zweistein2.minestats.models.multicraftapi.MulticraftServerApiResponse
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate
import java.net.URLEncoder
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class MulticraftAPI(
    val url: String,
    val user: String,
    val key: String,
) {
    val restTemplate = RestTemplate()
    val gson = GsonBuilder().create()

    companion object {
        fun getMulticraftEncodedAPIKey(parameterQuery: String, apiKey: String): String {
            val hasher = Mac.getInstance("HmacSHA256")
            hasher.init(SecretKeySpec(apiKey.toByteArray(Charsets.UTF_8), "HmacSHA256"))

            val hash = hasher.doFinal(parameterQuery.toByteArray(Charsets.UTF_8))

            return hash.toHex().lowercase()
        }

        fun ByteArray.toHex(): String = joinToString(separator = "") { eachByte -> "%02x".format(eachByte) }
    }

    fun getServerStatus(serverId: String, playerList: Boolean): MulticraftServerApiResponse? {
        return gson.fromJson(call("getServerStatus", mutableMapOf(Pair("id", serverId), Pair("player_list", if (playerList) "0" else "1"))),
            MulticraftServerApiResponse::class.java)
    }

    fun getServerResources(serverId: String): MulticraftServerApiResponse? {
        return gson.fromJson(call("getServerResources", mutableMapOf(Pair("id", serverId))),
            MulticraftServerApiResponse::class.java)
    }

    fun getServer(serverId: String): MulticraftServerApiResponse? {
        return gson.fromJson(call("getServer", mutableMapOf(Pair("id", serverId))),
            MulticraftServerApiResponse::class.java)
    }

    private fun call(method: String, parameters: MutableMap<String, String>): String {
        val request = HttpEntity<String>(HttpHeaders())

        parameters["_MulticraftAPIMethod"] = method
        parameters["_MulticraftAPIUser"] = user

        val apiKeySalt = StringBuilder()
        val query = StringBuilder()

        for (param in parameters.entries) {
            val parameterKey = param.key
            val parameterValue = param.value

            // The api key is hashed with all params put after each other (with their values)
            apiKeySalt.append(parameterKey).append(parameterValue)
            query.append("&").append(URLEncoder.encode(parameterKey, Charsets.UTF_8)).append("=").append(URLEncoder.encode(parameterValue, Charsets.UTF_8))
        }

        // Append apikey (build by a hash of the apikey and the concatinated params as salt)
        query.append("&_MulticraftAPIKey=").append(getMulticraftEncodedAPIKey(apiKeySalt.toString(), this.key))

        val result = restTemplate.exchange(
            "$url${query.replaceFirst(Regex.fromLiteral("&"), "?")}",
            HttpMethod.GET,
            request,
            String::class.java).body

        return result ?: ""
    }
}