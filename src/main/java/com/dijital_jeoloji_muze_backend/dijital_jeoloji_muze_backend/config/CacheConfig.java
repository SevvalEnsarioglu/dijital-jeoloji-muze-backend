package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

//görsel olan yerleri cache'lemek için
@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
                new ConcurrentMapCache("anasayfaList"),    // liste sorgular
                new ConcurrentMapCache("anasayfaDetail"),  // tekli detay sorgular
                new ConcurrentMapCache("eserList"),
                new ConcurrentMapCache("eserDetail")
        ));
        return cacheManager;
    }
}
