package com.example.onlinebanking.service;

import com.example.onlinebanking.model.User;
import com.example.onlinebanking.model.dto.AuthRequest;
import com.example.onlinebanking.model.dto.AuthResponse;
import com.example.onlinebanking.security.CustomUserDetails;
import com.example.onlinebanking.security.CustomUserDetailsService;
import com.example.onlinebanking.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * Unit tests for the {@link AuthService} class.
 *
 * <p>This test class verifies the behavior of the {@link AuthService#login(AuthRequest)} method
 * under different scenarios, such as successful authentication and authentication failure.
 * It uses Mockito to mock dependencies like {@link AuthenticationManager}, {@link JwtUtil},
 * and {@link CustomUserDetailsService}, and JUnit 5 for testing.
 *
 * <p>The test cases include:
 * <ul>
 *     <li>Successful authentication, where a valid {@link AuthResponse} is returned.</li>
 *     <li>Authentication failure, where an {@link AuthenticationException} is thrown.</li>
 * </ul>
 *
 * @author [Your Name]
 * @version 1.0
 * @since [Date]
 */
class AuthServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private CustomUserDetailsService userDetailsService;

    @InjectMocks
    private AuthService authService;

    /**
     * Sets up the test environment by initializing the mocks.
     * This method is executed before each test case to ensure a clean test environment.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the {@link AuthService#login(AuthRequest)} method for successful authentication.
     *
     * <p>This test verifies that the method returns a valid {@link AuthResponse} when
     * the authentication is successful, and that the appropriate methods are called
     * on the mocked dependencies.
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: Create an {@link AuthRequest} and mock the behavior of the
     *     {@link AuthenticationManager}, {@link CustomUserDetailsService}, and {@link JwtUtil}.</li>
     *     <li>Act: Call the {@link AuthService#login(AuthRequest)} method.</li>
     *     <li>Assert: Verify that the returned {@link AuthResponse} contains the expected token
     *     and that the mocked methods were called as expected.</li>
     * </ol>
     */
    @Test
    void testLogin_Success() {
        // Arrange
        AuthRequest authRequest = new AuthRequest("user", "password");
        User user = new User();
        CustomUserDetails userDetails = new CustomUserDetails(user);
        String token = "jwt-token";

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(userDetailsService.loadUserByUsername("user")).thenReturn(userDetails);
        when(jwtUtil.generateToken(userDetails)).thenReturn(token);

        // Act
        AuthResponse authResponse = authService.login(authRequest);

        // Assert
        assertNotNull(authResponse);
        assertEquals(token, authResponse.getToken());

        // Verify interactions
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userDetailsService, times(1)).loadUserByUsername("user");
        verify(jwtUtil, times(1)).generateToken(userDetails);
    }

    /**
     * Tests the {@link AuthService#login(AuthRequest)} method for authentication failure.
     *
     * <p>This test verifies that the method throws an {@link AuthenticationException} when
     * the authentication fails, and that the appropriate methods are called on the mocked
     * dependencies.
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: Create an {@link AuthRequest} with invalid credentials and mock the
     *     {@link AuthenticationManager} to throw an {@link AuthenticationException}.</li>
     *     <li>Act & Assert: Call the {@link AuthService#login(AuthRequest)} method and verify
     *     that an {@link AuthenticationException} is thrown.</li>
     *     <li>Verify: Ensure that the mocked methods for loading user details and generating
     *     tokens are never called.</li>
     * </ol>
     */
    @Test
    void testLogin_Failure() {
        // Arrange
        AuthRequest authRequest = new AuthRequest("user", "wrong-password");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new AuthenticationException("Bad credentials") {
                });

        // Act & Assert
        assertThrows(AuthenticationException.class, () -> authService.login(authRequest));

        // Verify interactions
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userDetailsService, never()).loadUserByUsername(anyString());
        verify(jwtUtil, never()).generateToken(any(CustomUserDetails.class));
    }
}