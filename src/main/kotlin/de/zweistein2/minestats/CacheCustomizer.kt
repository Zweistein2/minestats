package de.zweistein2.minestats

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.stereotype.Component

@Component
class CacheCustomizer: CacheManagerCustomizer<ConcurrentMapCacheManager> {
    override fun customize(cacheManager: ConcurrentMapCacheManager) {
        cacheManager.cacheNames = listOf("serverStats", "playerStats", "bestlist", "list", "blocks", "items", "mobs", "historic")
    }
}