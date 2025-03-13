package com.example.onlinebanking.controller;

import com.example.onlinebanking.model.dto.AuthRequest;
import com.example.onlinebanking.model.dto.AuthResponse;
import com.example.onlinebanking.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController is a REST controller that handles authentication-related operations for the Online Banking application.
 * It provides an endpoint for user login and authentication.
 *
 * <p>This class is annotated with {@link RestController} to indicate that it handles RESTful requests
 * and {@link RequestMapping} to define the base URL path for all endpoints in this controller.</p>
 *
 * <p>The primary endpoint provided by this controller is:
 * <ul>
 *     <li>{@code POST /api/auth/login}: Authenticates a user and returns a JWT token upon successful login.</li>
 * </ul>
 * </p>
 *
 * <p>This controller interacts with the {@link AuthService} to perform authentication logic and generate JWT tokens.</p>
 *
 * @see RestController
 * @see RequestMapping
 * @see AuthService
 * @see AuthRequest
 * @see AuthResponse
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Authenticates a user and returns a JWT token upon successful login.
     * <p>
     * This endpoint accepts an {@link AuthRequest} object in the request body, which contains the user's credentials
     * (e.g., username and password). It returns an {@link AuthResponse} object containing the JWT token if authentication
     * is successful.
     * </p>
     *
     * @param authRequest the authentication request containing the user's credentials, provided in the request body.
     * @return a {@link ResponseEntity} containing the {@link AuthResponse} with the JWT token and HTTP status {@link org.springframework.http.HttpStatus#OK}.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.login(authRequest));
    }
}