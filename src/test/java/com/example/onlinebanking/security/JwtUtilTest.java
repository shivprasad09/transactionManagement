package com.example.onlinebanking.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * Test class for {@link JwtUtil}.
 * This class tests the functionality of the JwtUtil class, including token generation,
 * extraction of claims, and token validation.
 *
 * <p>The test cases include:
 * <ul>
 *     <li>Extracting the username from a token.</li>
 *     <li>Extracting the expiration date from a token.</li>
 *     <li>Generating a token for a given user.</li>
 *     <li>Validating a token for a valid user.</li>
 *     <li>Validating a token for an invalid user.</li>
 *     <li>Validating an expired token.</li>
 * </ul>
 *
 * <p>This class uses Mockito to mock dependencies and JWT (JSON Web Token) utilities
 * to create and validate tokens.
 *
 * @author [Your Name]
 * @version 1.0
 * @since [Date]
 */
class JwtUtilTest {

    @InjectMocks
    private JwtUtil jwtUtil;

    private static final String SECRET_KEY = "secret";
    private static final String USERNAME = "john_doe";
    private static final String TOKEN = Jwts.builder()
            .setSubject(USERNAME)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();

    /**
     * Initializes the test environment by setting up mocks.
     * This method is executed before each test case to ensure a clean test environment.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the {@link JwtUtil#extractUsername(String)} method.
     * Verifies that the username is correctly extracted from the token.
     *
     * <p>Steps:
     * <ol>
     *     <li>Act: Call the {@link JwtUtil#extractUsername(String)} method with a valid token.</li>
     *     <li>Assert: Verify that the extracted username matches the expected username.</li>
     * </ol>
     */
    @Test
    void testExtractUsername() {
        // Act
        String extractedUsername = jwtUtil.extractUsername(TOKEN);

        // Assert
        assertEquals(USERNAME, extractedUsername, "The extracted username should match the expected username");
    }

    /**
     * Tests the {@link JwtUtil#extractExpiration(String)} method.
     * Verifies that the expiration date is correctly extracted from the token.
     *
     * <p>Steps:
     * <ol>
     *     <li>Act: Call the {@link JwtUtil#extractExpiration(String)} method with a valid token.</li>
     *     <li>Assert: Verify that the extracted expiration date is not null and is in the future.</li>
     * </ol>
     */
    @Test
    void testExtractExpiration() {
        // Act
        Date expirationDate = jwtUtil.extractExpiration(TOKEN);

        // Assert
        assertNotNull(expirationDate, "The expiration date should not be null");
        assertTrue(expirationDate.after(new Date()), "The expiration date should be in the future");
    }

    /**
     * Tests the {@link JwtUtil#generateToken(CustomUserDetails)} method.
     * Verifies that a valid token is generated for the provided user details.
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: Mock the {@link CustomUserDetails} and set up the username.</li>
     *     <li>Act: Call the {@link JwtUtil#generateToken(CustomUserDetails)} method.</li>
     *     <li>Assert: Verify that the generated token is not null and contains the correct username.</li>
     * </ol>
     */
    @Test
    void testGenerateToken() {
        // Arrange
        CustomUserDetails userDetails = mock(CustomUserDetails.class);
        when(userDetails.getUsername()).thenReturn(USERNAME);

        // Act
        String generatedToken = jwtUtil.generateToken(userDetails);

        // Assert
        assertNotNull(generatedToken, "The generated token should not be null");
        assertEquals(USERNAME, jwtUtil.extractUsername(generatedToken), "The generated token should contain the correct username");
    }

    /**
     * Tests the {@link JwtUtil#validateToken(String, UserDetails)} method with a valid token.
     * Verifies that the token is valid for the provided user details.
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: Mock the {@link UserDetails} and set up the username.</li>
     *     <li>Act: Call the {@link JwtUtil#validateToken(String, UserDetails)} method with a valid token.</li>
     *     <li>Assert: Verify that the token is valid for the provided user details.</li>
     * </ol>
     */
    @Test
    void testValidateToken_ValidToken() {
        // Arrange
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn(USERNAME);

        // Act
        boolean isValid = jwtUtil.validateToken(TOKEN, userDetails);

        // Assert
        assertTrue(isValid, "The token should be valid for the provided user details");
    }

    /**
     * Tests the {@link JwtUtil#validateToken(String, UserDetails)} method with an invalid token.
     * Verifies that the token is invalid for the provided user details.
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: Mock the {@link UserDetails} with a different username.</li>
     *     <li>Act: Call the {@link JwtUtil#validateToken(String, UserDetails)} method with the token.</li>
     *     <li>Assert: Verify that the token is invalid for the provided user details.</li>
     * </ol>
     */
    @Test
    void testValidateToken_InvalidToken() {
        // Arrange
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("invalid_user");

        // Act
        boolean isValid = jwtUtil.validateToken(TOKEN, userDetails);

        // Assert
        assertFalse(isValid, "The token should be invalid for the provided user details");
    }

    /**
     * Tests the {@link JwtUtil#validateToken(String, UserDetails)} method with an expired token.
     * Verifies that the token is invalid if it is expired.
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: Create an expired token and mock the {@link UserDetails}.</li>
     *     <li>Act: Call the {@link JwtUtil#validateToken(String, UserDetails)} method with the expired token.</li>
     *     <li>Assert: Verify that the token is invalid due to expiration.</li>
     * </ol>
     */
    @Test
    void testValidateToken_ExpiredToken() {
        // Arrange
        String expiredToken = Jwts.builder()
                .setSubject(USERNAME)
                .setIssuedAt(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24)) // 1 day ago
                .setExpiration(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 12)) // 12 hours ago
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn(USERNAME);

        // Act
        boolean isValid = jwtUtil.validateToken(expiredToken, userDetails);

        // Assert
        assertFalse(isValid, "The token should be invalid if it is expired");
    }
}