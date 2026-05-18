package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Value("${app.cors.allowed-origins}")
    private String allowedOrigins;

    @Value("${app.cors.allowed-methods}")
    private String allowedMethods;

    @Value("${app.cors.allowed-headers}")
    private String allowedHeaders;

    @Value("${app.cors.exposed-headers}")
    private String exposedHeaders;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns(getTrimmedArray(allowedOrigins))
                        .allowedMethods(getTrimmedArray(allowedMethods))
                        .allowedHeaders(getTrimmedArray(allowedHeaders))
                        .exposedHeaders(getTrimmedArray(exposedHeaders))
                        .allowCredentials(true);
            }
        };
    }

    private String[] getTrimmedArray(String value) {
        return java.util.Arrays.stream(value.split(","))
                .map(String::trim)
                .toArray(String[]::new);
    }
}