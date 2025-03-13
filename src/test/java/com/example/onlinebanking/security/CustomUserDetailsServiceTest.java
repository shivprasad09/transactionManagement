package com.example.onlinebanking.security;

import com.example.onlinebanking.model.User;
import com.example.onlinebanking.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
/**
 * Unit tests for the {@link com.example.onlinebanking.security.CustomUserDetailsService} class.
 *
 * <p>This test class verifies the behavior of the {@link CustomUserDetailsService#loadUserByUsername(String)} method
 * under different scenarios, such as when a user is found or when no user is found in the database.</p>
 *
 * <p>The tests in this class use Mockito to mock the {@link UserRepository} and verify the behavior of the
 * {@link CustomUserDetailsService} class.</p>
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 */
class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Sets up the test environment by initializing the mocks.
     * This method is executed before each test case to ensure a clean test environment.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the {@link CustomUserDetailsService#loadUserByUsername(String)} method when a user is found.
     *
     * <p>This test verifies that the method returns a {@link CustomUserDetails} object containing the user's details
     * when the user is found in the database.</p>
     *
     * <p>Steps:
     * <ol>
     *   <li>Arrange: Create a user and mock the {@link UserRepository} to return the user when queried by username.</li>
     *   <li>Act: Call the {@link CustomUserDetailsService#loadUserByUsername(String)} method.</li>
     *   <li>Assert: Verify that the returned {@link CustomUserDetails} object is not null and contains the correct username.</li>
     * </ol>
     * </p>
     */
    @Test
    void testLoadUserByUsername_UserFound() {
        // Arrange
        String username = "john_doe";
        User user = new User();
        user.setUsername(username);
        user.setPassword("password123");

        // Mock the repository method
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // Act
        CustomUserDetails result = customUserDetailsService.loadUserByUsername(username);

        // Assert
        assertNotNull(result, "UserDetails should not be null");
        assertEquals(username, result.getUsername(), "The username should match");
    }

    /**
     * Tests the {@link CustomUserDetailsService#loadUserByUsername(String)} method when no user is found.
     *
     * <p>This test verifies that the method throws a {@link UsernameNotFoundException} when the user is not found
     * in the database.</p>
     *
     * <p>Steps:
     * <ol>
     *   <li>Arrange: Mock the {@link UserRepository} to return an empty {@link Optional} when queried by username.</li>
     *   <li>Act & Assert: Call the {@link CustomUserDetailsService#loadUserByUsername(String)} method and verify that
     *       a {@link UsernameNotFoundException} is thrown.</li>
     * </ol>
     * </p>
     */
    @Test
    void testLoadUserByUsername_UserNotFound() {
        // Arrange
        String username = "non_existent_user";

        // Mock the repository method
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> customUserDetailsService.loadUserByUsername(username), "UsernameNotFoundException should be thrown");
    }
}