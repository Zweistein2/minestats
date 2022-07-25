package de.zweistein2.minestats

import de.zweistein2.minestats.components.MinestatProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.i18n.CookieLocaleResolver
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import java.util.*
import java.util.concurrent.TimeUnit

private const val MAX_LOCALE_COOKIE_TIME: Long = 90

@Configuration
class WebMvcConfig: WebMvcConfigurer {
    @Autowired
    lateinit var minestatProperties: MinestatProperties

    @Bean
    fun localeResolver(): LocaleResolver {
        val clr = CookieLocaleResolver()
        clr.cookieMaxAge = TimeUnit.DAYS.toSeconds(MAX_LOCALE_COOKIE_TIME).toInt()
        clr.setDefaultLocale(Locale.forLanguageTag(minestatProperties.locale))

        return clr
    }

    @Bean
    fun localeChangeInterceptor(): LocaleChangeInterceptor {
        val lci = LocaleChangeInterceptor()
        lci.paramName = "lang"

        return lci
    }

    @Bean("messageSource")
    fun messageSource(): MessageSource {
        val messageSource = ResourceBundleMessageSource()
        messageSource.setBasenames("lang/mobs", "lang/blocks", "lang/items", "lang/custom", "lang/messages", "lang/tag")
        messageSource.setDefaultEncoding(Charsets.UTF_8.displayName())
        messageSource.setUseCodeAsDefaultMessage(true)

        return messageSource
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(localeChangeInterceptor())
    }

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/dashboard").setViewName("dashboard")
    }
}