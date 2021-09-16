package de.zweistein2.minestats.controller

import com.google.gson.GsonBuilder
import de.zweistein2.minestats.components.BitbucketProperties
import de.zweistein2.minestats.models.bitbucket.BitbucketResponseModel
import de.zweistein2.minestats.utils.LocalDateTimeDeserializer
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import kotlin.system.measureTimeMillis
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate
import java.security.MessageDigest
import java.time.LocalDateTime
import kotlin.text.Charsets.UTF_8

@Controller
class DevstatController(
    val bitbucketProperties: BitbucketProperties
) {
    val restTemplate = RestTemplate()

    fun md5(str: String): ByteArray = MessageDigest.getInstance("MD5").digest(str.toByteArray(UTF_8))
    fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }

    @GetMapping("/devstats")
    fun getDevstats(model: Model): String {
        val runtimeInMilliseconds = measureTimeMillis {
            val limit = 50
            val gson = GsonBuilder().registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeDeserializer(bitbucketProperties)).create()

            val headers = HttpHeaders()
            headers.setBearerAuth(bitbucketProperties.apiToken)
            val request = HttpEntity<String>(headers)

            val response = gson.fromJson(restTemplate.exchange(
                bitbucketProperties.apiUrl + "projects/WEB/repos/minestats/commits/?until=dev&limit=$limit",
                HttpMethod.GET,
                request,
                String::class.java
            ).body, BitbucketResponseModel::class.java)

            val commits = response.values.toList().reversed()
            commits.forEach { it.committer.hash = md5(it.committer.emailAddress.trim().lowercase()).toHex() }

            model.addAttribute("commits", commits)
        }

        model.addAttribute("runtime", runtimeInMilliseconds)

        return "devstats"
    }
}