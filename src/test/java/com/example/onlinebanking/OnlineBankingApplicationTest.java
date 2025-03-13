package com.example.onlinebanking;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
/**
 * Unit tests for the {@link com.example.onlinebanking.OnlineBankingApplication} class.
 *
 * <p>This test class verifies that the Spring Boot application context loads successfully.
 * It ensures that the application starts up correctly without throwing any exceptions.</p>
 *
 * <p>The test is annotated with {@link org.springframework.boot.test.context.SpringBootTest}
 * to load the full application context for integration testing.</p>
 *
 * @author [Your Name]
 * @version 1.0
 * @since [Date]
 */
@SpringBootTest
class OnlineBankingApplicationTest {

    /**
     * Tests that the Spring Boot application context loads without throwing any exceptions.
     *
     * <p>This test ensures that the application starts up correctly by invoking the
     * {@link OnlineBankingApplication#main(String[])} method and verifying that no exceptions
     * are thrown during the process.</p>
     *
     * <p>Steps:
     * <ol>
     *   <li>Act: Call the {@link OnlineBankingApplication#main(String[])} method with an empty argument array.</li>
     *   <li>Assert: Verify that no exceptions are thrown during the execution.</li>
     * </ol>
     * </p>
     */
    @Test
    void contextLoads() {
        assertDoesNotThrow(() -> OnlineBankingApplication.main(new String[]{}), "The application context should load successfully");
    }
}