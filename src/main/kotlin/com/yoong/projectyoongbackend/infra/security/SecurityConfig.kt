package com.yoong.projectyoongbackend.infra.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
class SecurityConfig {

    @Bean
    fun filterChain(
        http: HttpSecurity,
    ): SecurityFilterChain {
        return http
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .csrf { it.disable() }
            .cors { }
            .authorizeHttpRequests {
                it.requestMatchers(
                    "/"
                ).permitAll()
                    .anyRequest().authenticated()
            }.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()

        configuration.allowedOrigins = listOf("http://localhost:3001")

        configuration.allowedMethods = listOf("POST", "GET", "DELETE", "PUT", "PATCH")

        configuration.allowedHeaders = listOf("*")

        configuration.allowCredentials = true

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}