package com.example.onlinebanking.model.dto;

/**
 * TransactionDTO is a Data Transfer Object (DTO) class representing a simplified view of a {@link Transaction}.
 * It is used to transfer transaction-related data between layers of the application, such as between the
 * controller and service layers, without exposing the entire entity.
 *
 * <p>This class includes fields such as source account, destination account, and transaction amount,
 * which are essential for transaction-related operations.
 *
 * <p>Key features:
 * <ul>
 *   <li>Provides a lightweight representation of a transaction.</li>
 *   <li>Encapsulates transaction-related data for secure and efficient transfer.</li>
 *   <li>Does not include sensitive or unnecessary details from the entity.</li>
 * </ul>
 *
 * @author [Your Name]
 * @version 1.0
 * @since [Date]
 * @see Transaction
 */
public class TransactionDTO {

    private String fromAccount;

    private String toAccount;

    private Double amount;

    // Getters and Setters

    /**
     * Gets the source account number for the transaction.
     *
     * @return the source account number.
     */
    public String getFromAccount() {
        return fromAccount;
    }

    /**
     * Sets the source account number for the transaction.
     *
     * @param fromAccount the source account number to set.
     */
    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    /**
     * Gets the destination account number for the transaction.
     *
     * @return the destination account number.
     */
    public String getToAccount() {
        return toAccount;
    }

    /**
     * Sets the destination account number for the transaction.
     *
     * @param toAccount the destination account number to set.
     */
    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    /**
     * Gets the amount of the transaction.
     *
     * @return the transaction amount.
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the transaction.
     *
     * @param amount the transaction amount to set.
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
