package com.example.onlinebanking.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springfox.documentation.spring.web.plugins.Docket;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for the {@link SwaggerConfig} class.
 *
 * <p>This test class verifies the behavior of the methods in the {@link SwaggerConfig} class.
 */
class SwaggerConfigTest {

    private SwaggerConfig swaggerConfig;

    @BeforeEach
    void setUp() {
        swaggerConfig = new SwaggerConfig();
    }

    /**
     * Tests the {@link SwaggerConfig#api()} method.
     *
     * <p>This test verifies that the method returns a non-null {@link Docket} instance.
     */
    @Test
    void testApi() {
        Docket docket = swaggerConfig.api();
        assertNotNull(docket, "Docket should not be null");
    }
}