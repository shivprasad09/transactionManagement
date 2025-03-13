package com.example.onlinebanking.exception;

/**
 * ResourceNotFoundException is a custom runtime exception used to indicate that a requested resource could not be found.
 * It extends {@link RuntimeException} and is typically thrown when an operation attempts to access a resource that does not exist.
 *
 * <p>This exception is commonly used in service or controller layers to handle cases where a resource (e.g., a user, account, or transaction)
 * is not found in the database or any other data source.</p>
 *
 * <p>The exception includes a custom message that provides details about the resource that could not be found.</p>
 *
 * @see RuntimeException
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new {@link ResourceNotFoundException} with the specified detail message.
     * <p>
     * The message is used to provide additional information about the resource that could not be found.
     * </p>
     *
     * @param message the detail message describing the resource that was not found.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}