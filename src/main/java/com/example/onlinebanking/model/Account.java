package com.example.onlinebanking.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Represents an account entity in the system.
 * This class is used to store account information such as account number, balance, associated user, and transactions.
 * It is mapped to a database table using JPA annotations.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 */
@Entity
public class Account {

    /**
     * The unique identifier for the account.
     * This field is automatically generated by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The account number of the account.
     * This field must be unique and cannot be null.
     */
    @Column(unique = true, nullable = false)
    private String accountNumber;

    /**
     * The balance of the account.
     * This field cannot be null and is represented as a BigDecimal for precision.
     */
    @Column(nullable = false)
    private BigDecimal balance;

    /**
     * The user associated with the account.
     * This field represents a many-to-one relationship with the User entity.
     * The user ID is stored in the "user_id" column in the database.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * A set of transactions associated with the account.
     * This field represents a one-to-many relationship with the Transaction entity.
     * The transactions are lazily fetched and cascading operations are applied.
     */
    @OneToMany(mappedBy = "fromAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Transaction> transactions;

    /**
     * Gets the unique identifier of the account.
     *
     * @return the account's ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the account.
     *
     * @param id the account's ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the account number of the account.
     *
     * @return the account number.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the account number of the account.
     *
     * @param accountNumber the account number.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets the balance of the account.
     *
     * @return the balance.
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Sets the balance of the account.
     *
     * @param balance the balance.
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * Gets the user associated with the account.
     *
     * @return the user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user associated with the account.
     *
     * @param user the user.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the set of transactions associated with the account.
     *
     * @return the set of transactions.
     */
    public Set<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Sets the set of transactions associated with the account.
     *
     * @param transactions the set of transactions.
     */
    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
}