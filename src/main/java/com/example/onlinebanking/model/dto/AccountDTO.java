package com.example.onlinebanking.model.dto;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) for representing account information.
 * This class is used to transfer account-related data between layers of the application,
 * such as between the controller and service layers, without exposing the underlying entity structure.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 */
public class AccountDTO {

    /**
     * The account number of the account.
     */
    private String accountNumber;

    /**
     * The balance of the account.
     */
    private BigDecimal balance;

    /**
     * The unique identifier of the user associated with the account.
     */
    private Long userId;

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
     * Gets the unique identifier of the user associated with the account.
     *
     * @return the user ID.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the unique identifier of the user associated with the account.
     *
     * @param userId the user ID.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}