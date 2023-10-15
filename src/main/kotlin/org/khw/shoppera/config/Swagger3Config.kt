package org.khw.shoppera.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration



@Configuration
class SpringDocConfig {
    @Bean
    fun apiInfo(): OpenAPI {
        return OpenAPI().info(Info().title("권희운 api").version("1.0.0"))
    }

    @Bean
    fun httpApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("http")
            .pathsToMatch("/**")
            .build()
    }
}