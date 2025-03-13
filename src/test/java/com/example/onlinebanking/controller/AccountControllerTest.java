package com.example.onlinebanking.controller;

import com.example.onlinebanking.model.Account;
import com.example.onlinebanking.service.AccountService;
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
 * Test class for {@link com.example.onlinebanking.controller.AccountController}.
 * This class contains unit tests to verify the functionality of the AccountController methods.
 * It uses Mockito to mock the {@link com.example.onlinebanking.service.AccountService} dependency
 * and tests the behavior of the controller in isolation.
 *
 * <p>Key test cases include:
 * <ul>
 *     <li>Creating a new account</li>
 *     <li>Retrieving an existing account by ID</li>
 *     <li>Handling the case when an account is not found</li>
 *     <li>Deleting an account</li>
 * </ul>
 *
 * <p>Each test method follows the Arrange-Act-Assert pattern to ensure clarity and maintainability.
 *
 * @see com.example.onlinebanking.controller.AccountController
 * @see com.example.onlinebanking.service.AccountService
 * @see org.mockito.Mockito
 */


class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the {@link AccountController#createAccount(Account)} method.
     * Verifies that the controller correctly creates an account and returns the created account.
     */
    @Test
    void testCreateAccount() {
        // Arrange
        Account account = new Account();
        account.setId(1L);
        account.setAccountNumber("123456789");
        account.setBalance(BigDecimal.valueOf(1000.0));

        when(accountService.createAccount(account)).thenReturn(account);

        // Act
        Account createdAccount = accountController.createAccount(account);

        // Assert
        assertNotNull(createdAccount);
        assertEquals(1L, createdAccount.getId());
        assertEquals("123456789", createdAccount.getAccountNumber());
        assertEquals(BigDecimal.valueOf(1000.0), createdAccount.getBalance());

        // Verify that the service method was called
        verify(accountService, times(1)).createAccount(account);
    }

    /**
     * Tests the {@link AccountController#getAccount(Long)} method when the account is found.
     * Verifies that the controller correctly retrieves and returns the account.
     */
    @Test
    void testGetAccount_Found() {
        // Arrange
        Long accountId = 1L;
        Account account = new Account();
        account.setId(accountId);
        account.setAccountNumber("123456789");
        account.setBalance(BigDecimal.valueOf(1000.0));

        when(accountService.getAccount(accountId)).thenReturn(Optional.of(account));

        // Act
        Optional<Account> result = accountController.getAccount(accountId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(accountId, result.get().getId());
        assertEquals("123456789", result.get().getAccountNumber());
        assertEquals(BigDecimal.valueOf(1000.0), result.get().getBalance());

        // Verify that the service method was called
        verify(accountService, times(1)).getAccount(accountId);
    }

    /**
     * Tests the {@link AccountController#getAccount(Long)} method when the account is not found.
     * Verifies that the controller correctly handles the case and returns an empty Optional.
     */
    @Test
    void testGetAccount_NotFound() {
        // Arrange
        Long accountId = 1L;
        when(accountService.getAccount(accountId)).thenReturn(Optional.empty());

        // Act
        Optional<Account> result = accountController.getAccount(accountId);

        // Assert
        assertFalse(result.isPresent());

        // Verify that the service method was called
        verify(accountService, times(1)).getAccount(accountId);
    }

    /**
     * Tests the {@link AccountController#deleteAccount(Long)} method.
     * Verifies that the controller correctly delegates the deletion request to the service.
     */
    @Test
    void testDeleteAccount() {
        // Arrange
        Long accountId = 1L;
        doNothing().when(accountService).deleteAccount(accountId);

        // Act
        accountController.deleteAccount(accountId);

        // Assert
        // Verify that the service method was called
        verify(accountService, times(1)).deleteAccount(accountId);
    }
}