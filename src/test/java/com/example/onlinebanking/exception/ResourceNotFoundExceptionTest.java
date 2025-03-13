package com.example.onlinebanking.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * Test class for {@link com.example.onlinebanking.exception.ResourceNotFoundException}.
 * This class contains unit tests to verify the behavior of the custom exception,
 * ensuring that the exception message is correctly set and retrieved.
 *
 * <p>Key test cases include:
 * <ul>
 *     <li>Verifying that the exception message is correctly set and retrieved</li>
 * </ul>
 *
 * <p>This test class follows the Arrange-Act-Assert pattern to ensure clarity and maintainability.
 *
 * @see com.example.onlinebanking.exception.ResourceNotFoundException
 */


public class ResourceNotFoundExceptionTest {

    /**
     * Tests the constructor of the {@link ResourceNotFoundException} class.
     * This test verifies that the exception message is correctly set and can be retrieved.
     *
     * <p>Steps:
     * <ol>
     *     <li>Define the expected error message.</li>
     *     <li>Create an instance of {@link ResourceNotFoundException} with the error message.</li>
     *     <li>Assert that the exception message matches the expected message.</li>
     * </ol>
     *
     * @Test annotation indicates that this method is a test case.
     */
    @Test
    public void testResourceNotFoundException() {
        // Arrange: Define the expected error message
        String errorMessage = "Resource not found";

        // Act: Create an instance of ResourceNotFoundException
        ResourceNotFoundException exception = new ResourceNotFoundException(errorMessage);

        // Assert: Verify that the exception message matches the expected message
        assertEquals(errorMessage, exception.getMessage());
    }
}