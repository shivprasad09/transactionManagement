package com.example.onlinebanking.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for the {@link SecurityConfig} class.
 *
 * <p>This test class verifies the behavior of the methods in the {@link SecurityConfig} class.
 */
class SecurityConfigTest {

    @Mock
    private AuthenticationConfiguration authenticationConfiguration;

    private SecurityConfig securityConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        securityConfig = new SecurityConfig();
    }


    /**
     * Tests the {@link SecurityConfig#passwordEncoder()} method.
     *
     * <p>This test verifies that the method returns a non-null {@link PasswordEncoder} instance.
     */
    @Test
    void testPasswordEncoder() {
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();
        assertNotNull(passwordEncoder, "PasswordEncoder should not be null");
    }
}