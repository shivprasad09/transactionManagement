package com.example.onlinebanking.repository;

import com.example.onlinebanking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing {@link User} entities.
 * This interface extends {@link JpaRepository} to provide CRUD operations and custom query methods for the User entity.
 * It is used to interact with the database and perform operations related to User entities.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their username.
     * This method performs a query to retrieve a user entity based on the provided username.
     *
     * @param username the username of the user to find.
     * @return an {@link Optional} containing the user if found, or an empty Optional if no user is found.
     */
    Optional<User> findByUsername(String username);
}