package com.example.onlinebanking.service;

import com.example.onlinebanking.model.Account;
import com.example.onlinebanking.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * Test class for {@link AccountService}.
 * This class tests the functionality of the AccountService class, including account creation,
 * retrieval, and deletion.
 *
 * <p>The test cases include:
 * <ul>
 *     <li>Creating a new account and verifying its details.</li>
 *     <li>Retrieving an existing account by its ID.</li>
 *     <li>Attempting to retrieve a non-existent account.</li>
 *     <li>Deleting an account by its ID.</li>
 * </ul>
 *
 * <p>This class uses Mockito to mock the {@link AccountRepository} and verify the interaction
 * between the service and the repository.
 *
 * @author [Your Name]
 * @version 1.0
 * @since [Date]
 */
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    /**
     * Initializes the test environment by setting up mocks.
     * This method is executed before each test case to ensure a clean test environment.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the {@link AccountService#createAccount(Account)} method.
     * Verifies that an account is successfully created and saved in the repository.
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: Create a mock account and set up the repository to return it when saved.</li>
     *     <li>Act: Call the {@link AccountService#createAccount(Account)} method.</li>
     *     <li>Assert: Verify that the returned account matches the expected details and that the repository's save method was called once.</li>
     * </ol>
     */
    @Test
    void testCreateAccount() {
        // Arrange
        Account account = new Account();
        account.setId(1L);
        account.setAccountNumber("123456789");
        account.setBalance(BigDecimal.valueOf(1000.0));

        when(accountRepository.save(account)).thenReturn(account);

        // Act
        Account savedAccount = accountService.createAccount(account);

        // Assert
        assertNotNull(savedAccount);
        assertEquals(1L, savedAccount.getId());
        assertEquals("123456789", savedAccount.getAccountNumber());
        assertEquals(0, BigDecimal.valueOf(1000.0).compareTo(savedAccount.getBalance()));

        // Verify that the repository's save method was called once
        verify(accountRepository, times(1)).save(account);
    }

    /**
     * Tests the {@link AccountService#getAccount(Long)} method when the account exists.
     * Verifies that the correct account is retrieved from the repository.
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: Create a mock account and set up the repository to return it when queried by ID.</li>
     *     <li>Act: Call the {@link AccountService#getAccount(Long)} method.</li>
     *     <li>Assert: Verify that the returned account matches the expected details and that the repository's findById method was called once.</li>
     * </ol>
     */
    @Test
    void testGetAccount() {
        // Arrange
        Account account = new Account();
        account.setId(1L);
        account.setAccountNumber("123456789");
        account.setBalance(BigDecimal.valueOf(100.0));

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        // Act
        Optional<Account> foundAccount = accountService.getAccount(1L);

        // Assert
        assertTrue(foundAccount.isPresent());
        assertEquals(1L, foundAccount.get().getId());
        assertEquals("123456789", foundAccount.get().getAccountNumber());
        assertEquals(0, BigDecimal.valueOf(100.0).compareTo(foundAccount.get().getBalance()));

        // Verify that the repository's findById method was called once
        verify(accountRepository, times(1)).findById(1L);
    }

    /**
     * Tests the {@link AccountService#getAccount(Long)} method when the account does not exist.
     * Verifies that an empty Optional is returned.
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: Set up the repository to return an empty Optional when queried by ID.</li>
     *     <li>Act: Call the {@link AccountService#getAccount(Long)} method.</li>
     *     <li>Assert: Verify that the returned Optional is empty and that the repository's findById method was called once.</li>
     * </ol>
     */
    @Test
    void testGetAccount_NotFound() {
        // Arrange
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Optional<Account> foundAccount = accountService.getAccount(1L);

        // Assert
        assertFalse(foundAccount.isPresent());

        // Verify that the repository's findById method was called once
        verify(accountRepository, times(1)).findById(1L);
    }

    /**
     * Tests the {@link AccountService#deleteAccount(Long)} method.
     * Verifies that the account is deleted from the repository.
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: Set up the repository to perform no action when deleteById is called.</li>
     *     <li>Act: Call the {@link AccountService#deleteAccount(Long)} method.</li>
     *     <li>Assert: Verify that the repository's deleteById method was called once.</li>
     * </ol>
     */
    @Test
    void testDeleteAccount() {
        // Arrange
        doNothing().when(accountRepository).deleteById(1L);

        // Act
        accountService.deleteAccount(1L);

        // Assert
        // Verify that the repository's deleteById method was called once
        verify(accountRepository, times(1)).deleteById(1L);
    }
}