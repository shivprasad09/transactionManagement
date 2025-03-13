package com.example.onlinebanking.repository;

import com.example.onlinebanking.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
/**
 * Test class for {@link UserRepository}.
 * This class tests the custom query method of the UserRepository interface.
 * It uses Mockito to mock the repository and verify the behavior of the
 * {@link UserRepository#findByUsername(String)} method under different scenarios.
 *
 * <p>The test cases include:
 * <ul>
 *     <li>Finding a user by a valid username.</li>
 *     <li>Attempting to find a user with a non-existent username.</li>
 *     <li>Handling a null username input.</li>
 * </ul>
 *
 * <p>This class is annotated with {@link DataJpaTest} to configure the necessary
 * Spring Data JPA components for testing.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 */
@DataJpaTest
class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the {@link UserRepository#findByUsername(String)} method when a user is found.
     * Verifies that the repository returns the correct user for the given username.
     * <p>
     * Steps:
     * <ol>
     *     <li>Arrange: Create a mock user and set up the repository to return it when queried.</li>
     *     <li>Act: Call the repository method with the username.</li>
     *     <li>Assert: Verify that the returned user matches the expected username.</li>
     * </ol>
     */
    @Test
    void testFindByUsername_UserFound() {
        // Arrange
        String username = "john_doe";
        User user = new User();
        user.setUsername(username);
        user.setPassword("password123");

        // Mock the repository method
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userRepository.findByUsername(username);

        // Assert
        assertTrue(result.isPresent(), "User should be found");
        assertEquals(username, result.get().getUsername(), "The username should match");
    }

    /**
     * Tests the {@link UserRepository#findByUsername(String)} method when no user is found.
     * Verifies that the repository returns an empty Optional.
     * <p>
     * Steps:
     * <ol>
     *     <li>Arrange: Set up the repository to return an empty Optional for a non-existent username.</li>
     *     <li>Act: Call the repository method with the non-existent username.</li>
     *     <li>Assert: Verify that the result is an empty Optional.</li>
     * </ol>
     */
    @Test
    void testFindByUsername_UserNotFound() {
        // Arrange
        String username = "non_existent_user";

        // Mock the repository method
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act
        Optional<User> result = userRepository.findByUsername(username);

        // Assert
        assertFalse(result.isPresent(), "User should not be found");
    }

    /**
     * Tests the {@link UserRepository#findByUsername(String)} method when the username is null.
     * Verifies that the repository handles null input gracefully by returning an empty Optional.
     * <p>
     * Steps:
     * <ol>
     *     <li>Arrange: Set up the repository to return an empty Optional for a null username.</li>
     *     <li>Act: Call the repository method with a null username.</li>
     *     <li>Assert: Verify that the result is an empty Optional.</li>
     * </ol>
     */
    @Test
    void testFindByUsername_NullUsername() {
        // Arrange
        String username = null;

        // Mock the repository method
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act
        Optional<User> result = userRepository.findByUsername(username);

        // Assert
        assertFalse(result.isPresent(), "User should not be found for null username");
    }
}