package de.zweistein2.minestats

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
@EnableCaching
@EnableScheduling
@EnableWebSecurity
class WebSecurityConfig: WebSecurityConfigurerAdapter() {
    @Bean
    fun cacheManager(): CacheManager? {
        return ConcurrentMapCacheManager("serverStats", "playerStats", "bestlist", "list", "blocks", "items", "mobs")
    }

    override fun configure(web: WebSecurity) {
        web.ignoring()
                .antMatchers("/js/**")
                .antMatchers("/css/**")
                .antMatchers("/img/**")
                .antMatchers("/fonts/**")
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/", "/server", "/bestlist", "/dashboard", "/list", "/player", "/blocks", "/items", "/mobs", "/devstats", "/event", "/halloween", "/christmas").permitAll()
                .anyRequest().authenticated()
                .and()
            .logout()
                .deleteCookies("JSESSIONID")
                .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
            .cors()
                .and()
            .csrf()
    }
}