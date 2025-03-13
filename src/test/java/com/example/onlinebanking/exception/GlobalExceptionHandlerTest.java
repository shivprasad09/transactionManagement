/**
 * JUnit 5 test class for {@link com.example.onlinebanking.exception.GlobalExceptionHandler}.
 * <p>
 * This class contains test cases to verify the functionality of the {@link GlobalExceptionHandler} class,
 * including its exception-handling methods for {@link ResourceNotFoundException} and generic exceptions.
 * It ensures that the exception handler returns the correct HTTP status codes and error details.
 * </p>
 *
 * <p>Key test cases include:
 * <ul>
 *     <li>Handling {@link ResourceNotFoundException} with HTTP status 404 (Not Found)</li>
 *     <li>Handling generic exceptions with HTTP status 500 (Internal Server Error)</li>
 * </ul>
 *
 * <p>Each test method follows the Arrange-Act-Assert pattern to ensure clarity and maintainability.
 *
 * @author Your Name
 * @version 1.0
 * @see com.example.onlinebanking.exception.GlobalExceptionHandler
 * @see com.example.onlinebanking.exception.ResourceNotFoundException
 * @see com.example.onlinebanking.exception.ErrorDetails
 * @since 2023-10-01
 */
package com.example.onlinebanking.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;
    private WebRequest mockWebRequest;

    /**
     * Initializes the test environment before each test method is executed.
     * <p>
     * This method sets up the {@link GlobalExceptionHandler} instance and mocks the {@link WebRequest} object.
     * </p>
     */
    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
        mockWebRequest = mock(WebRequest.class);
    }

    /**
     * Tests the {@link GlobalExceptionHandler#handleResourceNotFoundException(ResourceNotFoundException)} method.
     * <p>
     * This test verifies that the method correctly handles a {@link ResourceNotFoundException} by returning
     * a {@link ResponseEntity} with HTTP status 404 (Not Found) and the appropriate error details.
     * </p>
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: Create a {@link ResourceNotFoundException} and mock the {@link WebRequest}.</li>
     *     <li>Act: Call the {@link GlobalExceptionHandler#handleResourceNotFoundException} method.</li>
     *     <li>Assert: Verify that the response contains the correct HTTP status and error details.</li>
     * </ol>
     */
    @Test
    @DisplayName("Test handleResourceNotFoundException")
    void testHandleResourceNotFoundException() {
        // Arrange
        ResourceNotFoundException exception = new ResourceNotFoundException("Resource not found");
        when(mockWebRequest.getDescription(false)).thenReturn("Test request details");

        // Act
        ResponseEntity<ErrorDetails> response = globalExceptionHandler.handleResourceNotFoundException(exception);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        ErrorDetails errorDetails = response.getBody();
        assertNotNull(errorDetails);
        assertEquals("Resource not found", errorDetails.getMessage());
        assertEquals("Resource Not found", errorDetails.getDetails());
    }

    /**
     * Tests the {@link GlobalExceptionHandler#handleGlobalException(Exception, WebRequest)} method.
     * <p>
     * This test verifies that the method correctly handles a generic exception by returning
     * a {@link ResponseEntity} with HTTP status 500 (Internal Server Error) and the appropriate error details.
     * </p>
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: Create a generic exception and mock the {@link WebRequest}.</li>
     *     <li>Act: Call the {@link GlobalExceptionHandler#handleGlobalException} method.</li>
     *     <li>Assert: Verify that the response contains the correct HTTP status and error details.</li>
     * </ol>
     */
    @Test
    @DisplayName("Test handleGlobalException")
    void testHandleGlobalException() {
        // Arrange
        Exception exception = new Exception("Internal server error");
        when(mockWebRequest.getDescription(false)).thenReturn("Test request details");

        // Act
        ResponseEntity<ErrorDetails> response = globalExceptionHandler.handleGlobalException(exception, mockWebRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        ErrorDetails errorDetails = response.getBody();
        assertNotNull(errorDetails);
        assertEquals("Internal server error", errorDetails.getMessage());
        assertEquals("Test request details", errorDetails.getDetails());
    }
}