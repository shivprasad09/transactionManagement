package com.example.onlinebanking.service;

import com.example.onlinebanking.model.Account;
import com.example.onlinebanking.model.Transaction;
import com.example.onlinebanking.repository.AccountRepository;
import com.example.onlinebanking.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Service class for handling fund transfer operations between accounts.
 * This class provides the business logic for transferring funds from one account to another,
 * updating account balances, and recording the transaction in the database.
 * It interacts with the {@link AccountRepository} and {@link TransactionRepository} to perform database operations.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 */
@Service
public class TransferService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Transfers funds from one account to another.
     * This method performs the following steps:
     * 1. Validates the existence of the source and destination accounts.
     * 2. Checks if the source account has sufficient balance for the transfer.
     * 3. Updates the balances of both accounts.
     * 4. Records the transaction in the database.
     *
     * @param fromAccountNumber the account number of the source account.
     * @param toAccountNumber the account number of the destination account.
     * @param amount the amount to be transferred.
     * @throws RuntimeException if the source or destination account is not found, or if the source account has insufficient balance.
     */
    @Transactional
    public void transferFunds(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
        // Retrieve the source and destination accounts
        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber)
                .orElseThrow(() -> new RuntimeException("From account not found"));
        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber)
                .orElseThrow(() -> new RuntimeException("To account not found"));

        // Check if the source account has sufficient balance
        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        // Update account balances
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        // Save updated account balances
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        // Create and save the transaction record
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setType("TRANSFER");
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);

        transactionRepository.save(transaction);
    }
}