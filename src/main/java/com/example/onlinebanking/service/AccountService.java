package com.example.onlinebanking.service;

import com.example.onlinebanking.model.Account;
import com.example.onlinebanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing {@link Account} entities.
 * This class provides business logic and operations related to accounts, such as creating, retrieving, and deleting accounts.
 * It interacts with the {@link AccountRepository} to perform database operations.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 */
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Creates a new account and saves it to the database.
     *
     * @param account the account entity to be created.
     * @return the saved account entity.
     */
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    /**
     * Retrieves an account by its unique identifier.
     *
     * @param id the ID of the account to retrieve.
     * @return an {@link Optional} containing the account if found, or an empty Optional if no account is found.
     */
    public Optional<Account> getAccount(Long id) {
        return accountRepository.findById(id);
    }

    /**
     * Deletes an account by its unique identifier.
     *
     * @param id the ID of the account to delete.
     */
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}