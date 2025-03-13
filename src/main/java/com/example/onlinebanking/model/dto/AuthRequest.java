package com.example.onlinebanking.model.dto;

/**
 * AuthRequest is a Data Transfer Object (DTO) class used for handling authentication requests.
 * It encapsulates the username and password provided by the user during the login process.
 *
 * <p>This class is primarily used to transfer authentication data between the client and the server,
 * typically in the context of user login operations.
 *
 * <p>Key features:
 * <ul>
 *   <li>Provides a simple structure for sending authentication credentials.</li>
 *   <li>Encapsulates sensitive information like username and password for secure transfer.</li>
 *   <li>Used as an input for authentication processes in the application.</li>
 * </ul>
 *
 * @author [Your Name]
 * @version 1.0
 * @since [Date]
 */
public class AuthRequest {

    private String username;

    private String password;

    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

// Getters and Setters

    /**
     * Gets the username provided for authentication.
     *
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for authentication.
     *
     * @param username the username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password provided for authentication.
     *
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for authentication.
     *
     * @param password the password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
