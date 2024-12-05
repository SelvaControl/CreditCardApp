package com.teleapps.util;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Spring Boot Swagger API",
        version = "1.0",
        description = "Documentation of Spring Boot API"
    )
)
public class SwaggerConfig {
	
}