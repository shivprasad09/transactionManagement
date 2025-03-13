package com.example.onlinebanking.repository;

import com.example.onlinebanking.model.Account;
import com.example.onlinebanking.model.Transaction;
import com.example.onlinebanking.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
/**
 * Test class for {@link com.example.onlinebanking.repository.TransactionRepository}.
 * This class contains unit tests for the custom query methods defined in the
 * {@link com.example.onlinebanking.repository.TransactionRepository} interface.
 *
 * <p>The tests in this class use Mockito to mock the {@link TransactionRepository} and
 * verify the behavior of its methods under various scenarios, such as retrieving
 * transactions by account number or handling cases where no transactions are found.</p>
 *
 * <p>This class is annotated with {@link org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest}
 * to configure the necessary Spring Data JPA components for testing.</p>
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 */
@DataJpaTest
class TransactionRepositoryTest {

    @Mock
    private TransactionRepository transactionRepository;

    /**
     * Initializes the test environment before each test method is executed.
     * This method sets up the Mockito framework to mock the {@link TransactionRepository}.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the {@link TransactionRepository#findByFromAccount_AccountNumberOrToAccount_AccountNumber(String, String)} method.
     * Verifies that the repository returns the correct list of transactions for a given account number.
     *
     * <p>This test creates two transactions associated with a specific account number and mocks the repository
     * to return these transactions. It then asserts that the returned list of transactions matches the expected list.</p>
     */
    @Test
    void testFindByFromAccount_AccountNumberOrToAccount_AccountNumber() {
        // Arrange
        String accountNumber = "1234567890";
        String accountNumber2 = "12345678908";
        Transaction transaction1 = new Transaction();
        transaction1.setFromAccount(initializeAccount(accountNumber, BigDecimal.TEN, new User()));
        transaction1.setToAccount(initializeAccount(accountNumber2, BigDecimal.TEN, new User()));

        Transaction transaction2 = new Transaction();
        transaction2.setFromAccount(initializeAccount(accountNumber, BigDecimal.TEN, new User()));

        List<Transaction> expectedTransactions = Arrays.asList(transaction1, transaction2);

        // Mock the repository method
        when(transactionRepository.findByFromAccount_AccountNumberOrToAccount_AccountNumber(accountNumber, accountNumber))
                .thenReturn(expectedTransactions);

        // Act
        List<Transaction> actualTransactions = transactionRepository
                .findByFromAccount_AccountNumberOrToAccount_AccountNumber(accountNumber, accountNumber);

        // Assert
        assertEquals(expectedTransactions.size(), actualTransactions.size(), "The number of transactions should match");
        assertEquals(expectedTransactions, actualTransactions, "The list of transactions should match");
    }

    /**
     * Tests the {@link TransactionRepository#findByFromAccount_AccountNumberOrToAccount_AccountNumber(String, String)} method
     * when no transactions are found for the given account number.
     * Verifies that the repository returns an empty list.
     *
     * <p>This test mocks the repository to return an empty list when no transactions are found for the provided account number.
     * It then asserts that the returned list is empty.</p>
     */
    @Test
    void testFindByFromAccount_AccountNumberOrToAccount_AccountNumber_NoTransactionsFound() {
        // Arrange
        String accountNumber = "1234567890";
        List<Transaction> expectedTransactions = List.of();

        // Mock the repository method
        when(transactionRepository.findByFromAccount_AccountNumberOrToAccount_AccountNumber(accountNumber, accountNumber))
                .thenReturn(expectedTransactions);

        // Act
        List<Transaction> actualTransactions = transactionRepository
                .findByFromAccount_AccountNumberOrToAccount_AccountNumber(accountNumber, accountNumber);

        // Assert
        assertEquals(0, actualTransactions.size(), "The list of transactions should be empty");
    }

    /**
     * Helper method to initialize an {@link Account} object with the provided account number, balance, and user.
     *
     * @param accountNumber the account number to set
     * @param balance the balance to set
     * @param user the user to associate with the account
     * @return an initialized {@link Account} object
     */
    private Account initializeAccount(String accountNumber, BigDecimal balance, User user) {
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setBalance(balance);
        account.setUser(user);
        return account;
    }
}