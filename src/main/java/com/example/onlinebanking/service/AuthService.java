package com.example.onlinebanking.service;

import com.example.onlinebanking.model.dto.AuthRequest;
import com.example.onlinebanking.model.dto.AuthResponse;
import com.example.onlinebanking.security.CustomUserDetails;
import com.example.onlinebanking.security.CustomUserDetailsService;
import com.example.onlinebanking.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * AuthService is a service class responsible for handling authentication-related operations,
 * such as user login and JWT token generation.
 *
 * <p>This class uses Spring Security's {@link AuthenticationManager} to authenticate users
 * and the {@link JwtUtil} utility to generate JWT tokens upon successful authentication.
 *
 * <p>Key responsibilities include:
 * <ul>
 *   <li>Authenticating users using their credentials (username and password).</li>
 *   <li>Loading user details using the {@link CustomUserDetailsService}.</li>
 *   <li>Generating a JWT token for authenticated users.</li>
 *   <li>Returning an {@link AuthResponse} containing the generated token.</li>
 * </ul>
 *
 * @author [Your Name]
 * @version 1.0
 * @since [Date]
 * @see AuthenticationManager
 * @see JwtUtil
 * @see CustomUserDetailsService
 * @see AuthRequest
 * @see AuthResponse
 */
@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    /**
     * Authenticates a user using the provided credentials and generates a JWT token upon successful authentication.
     *
     * <p>This method performs the following steps:
     * <ol>
     *   <li>Authenticates the user using the {@link AuthenticationManager} and the provided username and password.</li>
     *   <li>Loads the user details using the {@link CustomUserDetailsService}.</li>
     *   <li>Generates a JWT token using the {@link JwtUtil} utility.</li>
     *   <li>Returns an {@link AuthResponse} containing the generated token.</li>
     * </ol>
     *
     * @param authRequest the {@link AuthRequest} object containing the user's credentials (username and password).
     * @return an {@link AuthResponse} object containing the JWT token.
     * @throws org.springframework.security.core.AuthenticationException if authentication fails.
     */
    public AuthResponse login(AuthRequest authRequest) {
        // Authenticate the user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        // Load user details
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(authRequest.getUsername());

        // Generate JWT token
        String token = jwtUtil.generateToken(userDetails);

        // Return the token in the response
        return new AuthResponse(token);
    }
}