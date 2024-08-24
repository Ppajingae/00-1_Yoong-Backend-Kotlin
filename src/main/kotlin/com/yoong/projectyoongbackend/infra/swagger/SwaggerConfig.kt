package com.yoong.projectyoongbackend.infra.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

//    @Bean
//    fun openAPI(): OpenAPI = OpenAPI()
//        .addSecurityItem(
//            SecurityRequirement().addList("Bearer Authentication")
//        )
//        .components(
//            Components().addSecuritySchemes(
//                "Bearer Authentication",
//                SecurityScheme()
//                    .type(SecurityScheme.Type.HTTP)
//                    .scheme("Bearer")
//                    .bearerFormat("JWT")
//                    .`in`(SecurityScheme.In.HEADER)
//                    .name("Authorization")
//            )
//        )
//        .info(
//            Info()
//                .title("High-v API Test")
//                .description("high-v application test")
//                .version("1.0.0")
//        )
}