package com.example.onlinebanking.controller;

import com.example.onlinebanking.model.dto.AuthRequest;
import com.example.onlinebanking.model.dto.AuthResponse;
import com.example.onlinebanking.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
/**
 * Test class for {@link com.example.onlinebanking.controller.AuthController}.
 * This class contains unit tests to verify the functionality of the authentication-related endpoints
 * exposed by the {@link AuthController}. It uses Mockito to mock the {@link AuthService} dependency
 * and tests the behavior of the controller in isolation.
 *
 * <p>Key test cases include:
 * <ul>
 *     <li>Successful user login and JWT token generation</li>
 * </ul>
 *
 * <p>Each test method follows the Arrange-Act-Assert pattern to ensure clarity and maintainability.
 *
 * @see com.example.onlinebanking.controller.AuthController
 * @see com.example.onlinebanking.service.AuthService
 * @see org.mockito.Mockito
 */


class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the {@link AuthController#login(AuthRequest)} method for a successful login scenario.
     * Verifies that the controller correctly handles the login request, returns a valid JWT token,
     * and responds with an HTTP status code of 200 (OK).
     */
    @Test
    void testLogin_Success() {
        // Arrange
        AuthRequest authRequest = new AuthRequest("testuser", "testpassword");

        AuthResponse authResponse = new AuthResponse("jwt-token");

        when(authService.login(authRequest)).thenReturn(authResponse);

        // Act
        ResponseEntity<AuthResponse> response = authController.login(authRequest);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue()); // Verify HTTP status code is 200 OK
        assertEquals("jwt-token", response.getBody().getToken()); // Verify the token in the response

        // Verify that the service method was called
        verify(authService, times(1)).login(authRequest);
    }
}