package com.example.onlinebanking.security;

import com.example.onlinebanking.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 * Unit tests for the {@link com.example.onlinebanking.security.CustomUserDetails} class.
 *
 * <p>This test class verifies the behavior of the methods in the {@link CustomUserDetails} class,
 * which implements the {@link org.springframework.security.core.userdetails.UserDetails} interface.</p>
 *
 * <p>The tests ensure that the {@link CustomUserDetails} class correctly encapsulates user details
 * and adheres to the contract defined by the {@link org.springframework.security.core.userdetails.UserDetails} interface.</p>
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 */
class CustomUserDetailsTest {

    private User user;
    private CustomUserDetails customUserDetails;

    /**
     * Sets up the test environment by initializing the test data.
     * This method is executed before each test case to ensure a clean test environment.
     */
    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        customUserDetails = new CustomUserDetails(user);
    }

    /**
     * Tests the {@link CustomUserDetails#getAuthorities()} method.
     *
     * <p>This test verifies that the method returns an empty collection of authorities,
     * as the {@link CustomUserDetails} class does not currently support roles or permissions.</p>
     */
    @Test
    void testGetAuthorities() {
        assertTrue(customUserDetails.getAuthorities().isEmpty(), "Authorities should be empty");
    }

    /**
     * Tests the {@link CustomUserDetails#getPassword()} method.
     *
     * <p>This test verifies that the method returns the correct password
     * as set in the {@link User} object.</p>
     */
    @Test
    void testGetPassword() {
        assertEquals("password", customUserDetails.getPassword(), "The password should match");
    }

    /**
     * Tests the {@link CustomUserDetails#getUsername()} method.
     *
     * <p>This test verifies that the method returns the correct username
     * as set in the {@link User} object.</p>
     */
    @Test
    void testGetUsername() {
        assertEquals("testuser", customUserDetails.getUsername(), "The username should match");
    }

    /**
     * Tests the {@link CustomUserDetails#isAccountNonExpired()} method.
     *
     * <p>This test verifies that the method returns true, indicating that the user's account
     * is not expired.</p>
     */
    @Test
    void testIsAccountNonExpired() {
        assertTrue(customUserDetails.isAccountNonExpired(), "Account should not be expired");
    }

    /**
     * Tests the {@link CustomUserDetails#isAccountNonLocked()} method.
     *
     * <p>This test verifies that the method returns true, indicating that the user's account
     * is not locked.</p>
     */
    @Test
    void testIsAccountNonLocked() {
        assertTrue(customUserDetails.isAccountNonLocked(), "Account should not be locked");
    }

    /**
     * Tests the {@link CustomUserDetails#isCredentialsNonExpired()} method.
     *
     * <p>This test verifies that the method returns true, indicating that the user's credentials
     * are not expired.</p>
     */
    @Test
    void testIsCredentialsNonExpired() {
        assertTrue(customUserDetails.isCredentialsNonExpired(), "Credentials should not be expired");
    }

    /**
     * Tests the {@link CustomUserDetails#isEnabled()} method.
     *
     * <p>This test verifies that the method returns true, indicating that the user's account
     * is enabled.</p>
     */
    @Test
    void testIsEnabled() {
        assertTrue(customUserDetails.isEnabled(), "Account should be enabled");
    }
}