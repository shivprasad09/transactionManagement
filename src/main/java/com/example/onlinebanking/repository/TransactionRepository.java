package com.example.onlinebanking.repository;

import com.example.onlinebanking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing {@link Transaction} entities.
 * This interface extends {@link JpaRepository} to provide CRUD operations and custom query methods for the Transaction entity.
 * It is used to interact with the database and perform operations related to Transaction entities.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Finds all transactions associated with a specific account, either as the sender (fromAccount) or the receiver (toAccount).
     * This method performs a query to retrieve a list of transactions based on the provided account numbers.
     *
     * @param fromAccount the account number of the sender account.
     * @param toAccount the account number of the receiver account.
     * @return a list of transactions where the account is either the sender or the receiver.
     */
    List<Transaction> findByFromAccount_AccountNumberOrToAccount_AccountNumber(String fromAccount, String toAccount);
}