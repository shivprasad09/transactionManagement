package com.example.onlinebanking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class for Spring Security.
 * This class defines security rules for the application, such as authentication and authorization requirements.
 * It also configures a password encoder for securely hashing passwords.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Configures the security filter chain for the application.
     * This method defines the following security rules:
     * 1. Disables CSRF protection (for simplicity, not recommended in production).
     * 2. Permits all requests to the `/api/auth/**` endpoint without authentication.
     * 3. Requires authentication for all other requests.
     *
     * @param http the {@link HttpSecurity} object to configure.
     * @return the configured {@link SecurityFilterChain}.
     * @throws Exception if an error occurs during configuration.
     */


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF using the new API
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // Allow public access to /api/auth/**
                        .anyRequest().authenticated() // Require authentication for all other requests
                );
        return http.build();
    }

    /**
     * Configures a password encoder for securely hashing passwords.
     * This method uses the {@link BCryptPasswordEncoder} for password hashing.
     *
     * @return the configured {@link PasswordEncoder}.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}