/**
 * JUnit 5 test class for {@link com.example.onlinebanking.exception.ErrorDetails}.
 * <p>
 * This class contains test cases to verify the functionality of the {@link ErrorDetails} class,
 * including its constructor, getters, and validation logic. It ensures that the class behaves
 * as expected when valid and invalid parameters are provided.
 * </p>
 *
 * <p>Key test cases include:
 * <ul>
 *     <li>Constructor initialization with valid parameters</li>
 *     <li>Validation of null or empty parameters in the constructor</li>
 *     <li>Verification of getter methods</li>
 * </ul>
 *
 * <p>Each test method follows the Arrange-Act-Assert pattern to ensure clarity and maintainability.
 *
 * @author Your Name
 * @version 1.0
 * @see com.example.onlinebanking.exception.ErrorDetails
 * @since 2023-10-01
 */
package com.example.onlinebanking.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ErrorDetailsTest {

    /**
     * Tests the constructor of {@link ErrorDetails} with valid parameters.
     * <p>
     * This test verifies that the constructor initializes the fields correctly when valid parameters
     * are provided.
     * </p>
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: Define valid parameters for the constructor.</li>
     *     <li>Act: Create an instance of {@link ErrorDetails} using the valid parameters.</li>
     *     <li>Assert: Verify that the fields are correctly initialized.</li>
     * </ol>
     */
    @Test
    @DisplayName("Test constructor with valid parameters")
    void testConstructorWithValidParameters() {
        // Arrange
        LocalDateTime timestamp = LocalDateTime.now();
        String message = "Test message";
        String details = "Test details";

        // Act
        ErrorDetails errorDetails = new ErrorDetails(timestamp, message, details);

        // Assert
        assertNotNull(errorDetails);
        assertEquals(timestamp, errorDetails.getTimestamp());
        assertEquals(message, errorDetails.getMessage());
        assertEquals(details, errorDetails.getDetails());
    }

    /**
     * Tests the constructor of {@link ErrorDetails} with a null timestamp.
     * <p>
     * This test verifies that the constructor throws an {@link IllegalArgumentException} when
     * the timestamp parameter is null.
     * </p>
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: Define a null timestamp and valid other parameters.</li>
     *     <li>Act & Assert: Verify that an {@link IllegalArgumentException} is thrown with the expected message.</li>
     * </ol>
     */
    @Test
    @DisplayName("Test constructor with null timestamp")
    void testConstructorWithNullTimestamp() {
        // Arrange
        LocalDateTime timestamp = null;
        String message = "Test message";
        String details = "Test details";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new ErrorDetails(timestamp, message, details);
        });

        assertEquals("Timestamp cannot be null", exception.getMessage());
    }

    /**
     * Tests the constructor of {@link ErrorDetails} with a null message.
     * <p>
     * This test verifies that the constructor throws an {@link IllegalArgumentException} when
     * the message parameter is null.
     * </p>
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: Define a null message and valid other parameters.</li>
     *     <li>Act & Assert: Verify that an {@link IllegalArgumentException} is thrown with the expected message.</li>
     * </ol>
     */
    @Test
    @DisplayName("Test constructor with null message")
    void testConstructorWithNullMessage() {
        // Arrange
        LocalDateTime timestamp = LocalDateTime.now();
        String message = null;
        String details = "Test details";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new ErrorDetails(timestamp, message, details);
        });

        assertEquals("Message cannot be null or empty", exception.getMessage());
    }

    /**
     * Tests the constructor of {@link ErrorDetails} with an empty message.
     * <p>
     * This test verifies that the constructor throws an {@link IllegalArgumentException} when
     * the message parameter is empty.
     * </p>
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: Define an empty message and valid other parameters.</li>
     *     <li>Act & Assert: Verify that an {@link IllegalArgumentException} is thrown with the expected message.</li>
     * </ol>
     */
    @Test
    @DisplayName("Test constructor with empty message")
    void testConstructorWithEmptyMessage() {
        // Arrange
        LocalDateTime timestamp = LocalDateTime.now();
        String message = "";
        String details = "Test details";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new ErrorDetails(timestamp, message, details);
        });

        assertEquals("Message cannot be null or empty", exception.getMessage());
    }

    /**
     * Tests the constructor of {@link ErrorDetails} with null details.
     * <p>
     * This test verifies that the constructor throws an {@link IllegalArgumentException} when
     * the details parameter is null.
     * </p>
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: Define null details and valid other parameters.</li>
     *     <li>Act & Assert: Verify that an {@link IllegalArgumentException} is thrown with the expected message.</li>
     * </ol>
     */
    @Test
    @DisplayName("Test constructor with null details")
    void testConstructorWithNullDetails() {
        // Arrange
        LocalDateTime timestamp = LocalDateTime.now();
        String message = "Test message";
        String details = null;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new ErrorDetails(timestamp, message, details);
        });

        assertEquals("Details cannot be null or empty", exception.getMessage());
    }

    /**
     * Tests the constructor of {@link ErrorDetails} with empty details.
     * <p>
     * This test verifies that the constructor throws an {@link IllegalArgumentException} when
     * the details parameter is empty.
     * </p>
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: Define empty details and valid other parameters.</li>
     *     <li>Act & Assert: Verify that an {@link IllegalArgumentException} is thrown with the expected message.</li>
     * </ol>
     */
    @Test
    @DisplayName("Test constructor with empty details")
    void testConstructorWithEmptyDetails() {
        // Arrange
        LocalDateTime timestamp = LocalDateTime.now();
        String message = "Test message";
        String details = "";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new ErrorDetails(timestamp, message, details);
        });

        assertEquals("Details cannot be null or empty", exception.getMessage());
    }

    /**
     * Tests the getter methods of {@link ErrorDetails}.
     * <p>
     * This test verifies that the getter methods return the correct values after the object is constructed.
     * </p>
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: Define valid parameters and create an instance of {@link ErrorDetails}.</li>
     *     <li>Act & Assert: Verify that the getter methods return the expected values.</li>
     * </ol>
     */
    @Test
    @DisplayName("Test getter methods")
    void testGetters() {
        // Arrange
        LocalDateTime timestamp = LocalDateTime.now();
        String message = "Test message";
        String details = "Test details";

        ErrorDetails errorDetails = new ErrorDetails(timestamp, message, details);

        // Act & Assert
        assertEquals(timestamp, errorDetails.getTimestamp());
        assertEquals(message, errorDetails.getMessage());
        assertEquals(details, errorDetails.getDetails());
    }
}