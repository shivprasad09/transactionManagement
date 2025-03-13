package com.example.onlinebanking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * The {@code GlobalExceptionHandler} class is a global exception handler that centralizes the handling of exceptions
 * across the application. It uses Spring's {@link ControllerAdvice} to intercept exceptions thrown by controllers
 * and return consistent error responses.
 *
 * <p>This class provides methods to handle specific exceptions, such as {@link ResourceNotFoundException},
 * as well as a fallback method to handle all other exceptions.
 *
 * @see ControllerAdvice
 * @see ExceptionHandler
 * @see ResourceNotFoundException
 * @see ErrorDetails
 * @since 2023-10-01
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link ResourceNotFoundException} by returning a structured error response with HTTP status 404 (Not Found).
     *
     * <p>This method creates an {@link ErrorDetails} object containing the timestamp, exception message, and request details,
     * and returns it as part of a {@link ResponseEntity} with the appropriate HTTP status.
     *
     * @param ex the {@link ResourceNotFoundException} instance that was thrown.
     * @return a {@link ResponseEntity} containing the error details and HTTP status 404 (Not Found).
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), "Resource Not found");
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles all other exceptions by returning a structured error response with HTTP status 500 (Internal Server Error).
     *
     * <p>This method acts as a fallback for any unhandled exceptions. It creates an {@link ErrorDetails} object
     * containing the timestamp, exception message, and request details, and returns it as part of a
     * {@link ResponseEntity} with the appropriate HTTP status.
     *
     * @param ex      the {@link Exception} instance that was thrown.
     * @param request the {@link WebRequest} object containing details about the request that caused the exception.
     * @return a {@link ResponseEntity} containing the error details and HTTP status 500 (Internal Server Error).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles {@link RuntimeException} by returning a structured error response with HTTP status 400 (Bad Request).
     *
     * <p>This method creates a response entity containing the exception message and returns it with the appropriate HTTP status.
     *
     * @param ex the {@link RuntimeException} instance that was thrown.
     * @return a {@link ResponseEntity} containing the error message and HTTP status 400 (Bad Request).
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}