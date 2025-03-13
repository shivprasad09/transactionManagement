package com.example.onlinebanking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration class for Swagger API documentation.
 * This class sets up Swagger to automatically generate API documentation for the application.
 * It configures the Docket bean to scan the controller package and include all endpoints in the documentation.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Configures the Swagger Docket bean.
     * This method sets up Swagger to:
     * 1. Use the Swagger 2.0 documentation type.
     * 2. Scan the `com.securebank.controller` package for API endpoints.
     * 3. Include all paths in the documentation.
     *
     * @return the configured {@link Docket} bean.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.securebank.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}