package com.example.onlinebanking.controller;

import com.example.onlinebanking.model.Account;
import com.example.onlinebanking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * REST controller for managing {@link Account} entities.
 * This class exposes endpoints for creating, retrieving, and deleting accounts.
 * It interacts with the {@link AccountService} to perform business logic operations.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * Creates a new account.
     * This endpoint accepts a JSON representation of an account and saves it to the database.
     *
     * @param account the account entity to be created, provided in the request body.
     * @return the created account entity.
     */
    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    /**
     * Retrieves an account by its unique identifier.
     * This endpoint returns the account details if the account is found.
     *
     * @param id the ID of the account to retrieve, provided as a path variable.
     * @return an {@link Optional} containing the account if found, or an empty Optional if no account is found.
     */
    @GetMapping("/{id}")
    public Optional<Account> getAccount(@PathVariable Long id) {
        return accountService.getAccount(id);
    }

    /**
     * Deletes an account by its unique identifier.
     * This endpoint removes the account from the database if it exists.
     *
     * @param id the ID of the account to delete, provided as a path variable.
     */
    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }
}