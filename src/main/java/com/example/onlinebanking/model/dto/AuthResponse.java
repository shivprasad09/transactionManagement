package com.example.onlinebanking.model.dto;

/**
 * AuthResponse is a Data Transfer Object (DTO) class used for handling authentication responses.
 * It encapsulates the JWT token generated after a successful authentication process.
 *
 * <p>This class is primarily used to transfer the authentication token from the server to the client,
 * typically in response to a successful login operation.
 *
 * <p>Key features:
 * <ul>
 *   <li>Provides a simple structure for sending the authentication token.</li>
 *   <li>Encapsulates the token for secure transfer to the client.</li>
 *   <li>Used as an output for authentication processes in the application.</li>
 * </ul>
 *
 * @author [Your Name]
 * @version 1.0
 * @since [Date]
 */
public class AuthResponse {

    private String token;

    /**
     * Constructs an AuthResponse object with the provided JWT token.
     *
     * @param token the JWT token generated after successful authentication.
     */
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getters and Setters

    /**
     * Gets the JWT token.
     *
     * @return the JWT token.
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the JWT token.
     *
     * @param token the JWT token to set.
     */
    public void setToken(String token) {
        this.token = token;
    }
}
