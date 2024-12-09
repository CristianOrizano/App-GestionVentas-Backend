package com.gestionventas.shared.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        String securitySchemaName = "bearerAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("Gestion Ventas")
                        .version("v1")
                        .description("Backend gestion de ventas")
                        .contact(new Contact()
                                .name("Cristian Orizano")))
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(securitySchemaName)// Agregar el requisito de seguridad
                )
                 .components(
                new Components().addSecuritySchemes(
                        securitySchemaName,
                        new SecurityScheme()
                                .name(securitySchemaName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT") // Definir el esquema Bearer para el token JWT
                )
        );
    }
}
