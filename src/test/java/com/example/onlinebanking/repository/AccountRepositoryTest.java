/**
 * JUnit 5 test class for {@link com.example.onlinebanking.repository.AccountRepository}.
 * <p>
 * This class contains test cases to verify the functionality of the {@link AccountRepository} class,
 * specifically focusing on the retrieval of accounts by account number. It uses Spring's {@link DataJpaTest}
 * annotation to configure an in-memory database and {@link TestEntityManager} to manage test data.
 * </p>
 *
 * <p>Key test cases include:
 * <ul>
 *     <li>Finding an account by its account number when the account exists</li>
 *     <li>Handling the case when an account is not found by its account number</li>
 * </ul>
 *
 * <p>Each test method follows the Arrange-Act-Assert pattern to ensure clarity and maintainability.
 *
 * @author Your Name
 * @version 1.0
 * @see com.example.onlinebanking.repository.AccountRepository
 * @see com.example.onlinebanking.model.Account
 * @see com.example.onlinebanking.model.User
 * @see org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
 * @see org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
 * @since 2023-10-01
 */
package com.example.onlinebanking.repository;

import com.example.onlinebanking.model.Account;
import com.example.onlinebanking.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    private Account account;
    private User user;

    /**
     * Initializes the test environment before each test method is executed.
     * <p>
     * This method sets up a test user and account in the in-memory database using {@link TestEntityManager}.
     * </p>
     */
    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        entityManager.persistAndFlush(user);

        account = new Account();
        account.setAccountNumber("123456");
        account.setBalance(new BigDecimal("1000.00"));
        account.setUser(user);
        entityManager.persistAndFlush(account);
    }

    /**
     * Tests the {@link AccountRepository#findByAccountNumber(String)} method when the account exists.
     * <p>
     * This test verifies that the method correctly retrieves an account by its account number
     * when the account is present in the database.
     * </p>
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: The account is already persisted in the database during setup.</li>
     *     <li>Act: Call the {@link AccountRepository#findByAccountNumber} method with the account number.</li>
     *     <li>Assert: Verify that the returned account matches the expected account.</li>
     * </ol>
     */
    @Test
    void testFindByAccountNumber_Success() {
        // Act
        Optional<Account> foundAccount = accountRepository.findByAccountNumber("123456");

        // Assert
        assertTrue(foundAccount.isPresent());
        assertEquals(account.getAccountNumber(), foundAccount.get().getAccountNumber());
    }

    /**
     * Tests the {@link AccountRepository#findByAccountNumber(String)} method when the account does not exist.
     * <p>
     * This test verifies that the method correctly handles the case when no account is found
     * for the given account number.
     * </p>
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: The account is already persisted in the database during setup.</li>
     *     <li>Act: Call the {@link AccountRepository#findByAccountNumber} method with a non-existent account number.</li>
     *     <li>Assert: Verify that the returned {@link Optional} is empty.</li>
     * </ol>
     */
    @Test
    void testFindByAccountNumber_NotFound() {
        // Act
        Optional<Account> foundAccount = accountRepository.findByAccountNumber("654321");

        // Assert
        assertFalse(foundAccount.isPresent());
    }
}