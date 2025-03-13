package com.example.onlinebanking.repository;

import com.example.onlinebanking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing {@link Account} entities.
 * This interface extends {@link JpaRepository} to provide CRUD operations and custom query methods for the Account entity.
 * It is used to interact with the database and perform operations related to Account entities.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Finds an account by its account number.
     * This method performs a query to retrieve an account entity based on the provided account number.
     *
     * @param accountNumber the account number of the account to find.
     * @return an {@link Optional} containing the account if found, or an empty Optional if no account is found.
     */
    Optional<Account> findByAccountNumber(String accountNumber);
}