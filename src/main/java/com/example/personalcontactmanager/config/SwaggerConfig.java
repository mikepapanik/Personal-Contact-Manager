package com.example.personalcontactmanager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up Swagger API documentation.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configures the custom OpenAPI instance for Swagger documentation.
     *
     * @return an instance of OpenAPI with custom settings
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("basicAuth"))
                .components(new io.swagger.v3.oas.models.Components().addSecuritySchemes("basicAuth",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
                .info(new Info()
                        .title("Personal Contact Manager API")
                        .version("1.0")
                        .description("API documentation for Personal Contact Manager"));
    }
}
