package com.example.onlinebanking.service;

import com.example.onlinebanking.model.Account;
import com.example.onlinebanking.model.Transaction;
import com.example.onlinebanking.repository.AccountRepository;
import com.example.onlinebanking.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
/**
 * Unit tests for the {@link TransferService} class.
 *
 * <p>This test class verifies the behavior of the {@link TransferService#transferFunds(String, String, BigDecimal)} method
 * under different scenarios, such as successful fund transfers, account not found errors, and insufficient balance errors.
 * It uses Mockito to mock the {@link AccountRepository} and {@link TransactionRepository}, and JUnit 5 for testing.
 *
 * <p>The test cases include:
 * <ul>
 *     <li>Successful fund transfer between two accounts.</li>
 *     <li>Attempting to transfer funds when the source account is not found.</li>
 *     <li>Attempting to transfer funds when the destination account is not found.</li>
 *     <li>Attempting to transfer funds when the source account has insufficient balance.</li>
 * </ul>
 *
 * @author [Your Name]
 * @version 1.0
 * @since [Date]
 */
class TransferServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransferService transferService;

    /**
     * Sets up the test environment by initializing the mocks.
     * This method is executed before each test case to ensure a clean test environment.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the {@link TransferService#transferFunds(String, String, BigDecimal)} method for a successful fund transfer.
     *
     * <p>This test verifies that the method correctly updates the balances of the source and destination accounts,
     * saves the transaction, and calls the appropriate repository methods.
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: Create mock accounts and set up the repository to return them when queried by account number.</li>
     *     <li>Act: Call the {@link TransferService#transferFunds(String, String, BigDecimal)} method.</li>
     *     <li>Assert: Verify that the account balances are updated correctly and that the repository methods are called as expected.</li>
     * </ol>
     */
    @Test
    void testTransferFunds_Success() {
        // Arrange
        String fromAccountNumber = "123456789";
        String toAccountNumber = "987654321";
        BigDecimal amount = BigDecimal.valueOf(500.0);

        Account fromAccount = new Account();
        fromAccount.setAccountNumber(fromAccountNumber);
        fromAccount.setBalance(BigDecimal.valueOf(1000.0));

        Account toAccount = new Account();
        toAccount.setAccountNumber(toAccountNumber);
        toAccount.setBalance(BigDecimal.valueOf(2000.0));

        when(accountRepository.findByAccountNumber(fromAccountNumber)).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findByAccountNumber(toAccountNumber)).thenReturn(Optional.of(toAccount));

        // Act
        transferService.transferFunds(fromAccountNumber, toAccountNumber, amount);

        // Assert
        // Verify account balances are updated
        assertEquals(BigDecimal.valueOf(500.0), fromAccount.getBalance()); // 1000 - 500 = 500
        assertEquals(BigDecimal.valueOf(2500.0), toAccount.getBalance()); // 2000 + 500 = 2500

        // Verify repository methods are called
        verify(accountRepository, times(1)).findByAccountNumber(fromAccountNumber);
        verify(accountRepository, times(1)).findByAccountNumber(toAccountNumber);
        verify(accountRepository, times(1)).save(fromAccount);
        verify(accountRepository, times(1)).save(toAccount);
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    /**
     * Tests the {@link TransferService#transferFunds(String, String, BigDecimal)} method when the source account is not found.
     *
     * <p>This test verifies that the method throws a {@link RuntimeException} with the message "From account not found"
     * when the source account does not exist.
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: Set up the repository to return an empty Optional for the source account.</li>
     *     <li>Act & Assert: Call the {@link TransferService#transferFunds(String, String, BigDecimal)} method and verify
     *     that a {@link RuntimeException} is thrown with the expected message.</li>
     *     <li>Verify: Ensure that the repository methods for saving accounts and transactions are never called.</li>
     * </ol>
     */
    @Test
    void testTransferFunds_SourceAccountNotFound() {
        // Arrange
        String fromAccountNumber = "123456789";
        String toAccountNumber = "987654321";
        BigDecimal amount = BigDecimal.valueOf(500.0);

        when(accountRepository.findByAccountNumber(fromAccountNumber)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            transferService.transferFunds(fromAccountNumber, toAccountNumber, amount);
        });

        assertEquals("From account not found", exception.getMessage());

        // Verify repository methods are called
        verify(accountRepository, times(1)).findByAccountNumber(fromAccountNumber);
        verify(accountRepository, never()).findByAccountNumber(toAccountNumber);
        verify(accountRepository, never()).save(any(Account.class));
        verify(transactionRepository, never()).save(any(Transaction.class));
    }

    /**
     * Tests the {@link TransferService#transferFunds(String, String, BigDecimal)} method when the destination account is not found.
     *
     * <p>This test verifies that the method throws a {@link RuntimeException} with the message "To account not found"
     * when the destination account does not exist.
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: Create a mock source account and set up the repository to return an empty Optional for the destination account.</li>
     *     <li>Act & Assert: Call the {@link TransferService#transferFunds(String, String, BigDecimal)} method and verify
     *     that a {@link RuntimeException} is thrown with the expected message.</li>
     *     <li>Verify: Ensure that the repository methods for saving accounts and transactions are never called.</li>
     * </ol>
     */
    @Test
    void testTransferFunds_DestinationAccountNotFound() {
        // Arrange
        String fromAccountNumber = "123456789";
        String toAccountNumber = "987654321";
        BigDecimal amount = BigDecimal.valueOf(500.0);

        Account fromAccount = new Account();
        fromAccount.setAccountNumber(fromAccountNumber);
        fromAccount.setBalance(BigDecimal.valueOf(1000.0));

        when(accountRepository.findByAccountNumber(fromAccountNumber)).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findByAccountNumber(toAccountNumber)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            transferService.transferFunds(fromAccountNumber, toAccountNumber, amount);
        });

        assertEquals("To account not found", exception.getMessage());

        // Verify repository methods are called
        verify(accountRepository, times(1)).findByAccountNumber(fromAccountNumber);
        verify(accountRepository, times(1)).findByAccountNumber(toAccountNumber);
        verify(accountRepository, never()).save(any(Account.class));
        verify(transactionRepository, never()).save(any(Transaction.class));
    }

    /**
     * Tests the {@link TransferService#transferFunds(String, String, BigDecimal)} method when the source account has insufficient balance.
     *
     * <p>This test verifies that the method throws a {@link RuntimeException} with the message "Insufficient balance"
     * when the source account does not have enough funds to complete the transfer.
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: Create mock accounts and set up the repository to return them when queried by account number.</li>
     *     <li>Act & Assert: Call the {@link TransferService#transferFunds(String, String, BigDecimal)} method and verify
     *     that a {@link RuntimeException} is thrown with the expected message.</li>
     *     <li>Verify: Ensure that the repository methods for saving accounts and transactions are never called.</li>
     * </ol>
     */
    @Test
    void testTransferFunds_InsufficientBalance() {
        // Arrange
        String fromAccountNumber = "123456789";
        String toAccountNumber = "987654321";
        BigDecimal amount = BigDecimal.valueOf(1500.0);

        Account fromAccount = new Account();
        fromAccount.setAccountNumber(fromAccountNumber);
        fromAccount.setBalance(BigDecimal.valueOf(1000.0));

        Account toAccount = new Account();
        toAccount.setAccountNumber(toAccountNumber);
        toAccount.setBalance(BigDecimal.valueOf(2000.0));

        when(accountRepository.findByAccountNumber(fromAccountNumber)).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findByAccountNumber(toAccountNumber)).thenReturn(Optional.of(toAccount));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            transferService.transferFunds(fromAccountNumber, toAccountNumber, amount);
        });

        assertEquals("Insufficient balance", exception.getMessage());

        // Verify repository methods are called
        verify(accountRepository, times(1)).findByAccountNumber(fromAccountNumber);
        verify(accountRepository, times(1)).findByAccountNumber(toAccountNumber);
        verify(accountRepository, never()).save(any(Account.class));
        verify(transactionRepository, never()).save(any(Transaction.class));
    }
}