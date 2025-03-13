/**
 * Unit tests for the {@link com.example.onlinebanking.controller.TransferController} class.
 *
 * <p>This test class uses Mockito to mock dependencies and Spring's MockMvc for testing
 * the REST endpoints. It verifies the behavior of the {@link TransferController#transferFunds(String, String, BigDecimal)}
 * method under different scenarios, including successful transfers and transfer failures.
 *
 * <p>Key test cases include:
 * <ul>
 *     <li>Successful transfer of funds between accounts</li>
 *     <li>Transfer failure due to insufficient balance or other errors</li>
 * </ul>
 *
 * <p>Each test method follows the Arrange-Act-Assert pattern to ensure clarity and maintainability.
 *
 * @see com.example.onlinebanking.controller.TransferController
 * @see com.example.onlinebanking.service.TransferService
 * @see org.mockito.Mockito
 * @see org.springframework.test.web.servlet.MockMvc
 */
package com.example.onlinebanking.controller;

import com.example.onlinebanking.service.TransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TransferControllerTest {

    @Mock
    private TransferService transferService;

    @InjectMocks
    private TransferController transferController;

    private MockMvc mockMvc;

    /**
     * Sets up the test environment by initializing the mocks and MockMvc.
     * This method is executed before each test case to ensure a clean test environment.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transferController).build();
    }

    /**
     * Tests the {@link TransferController#transferFunds(String, String, BigDecimal)} method for a successful transfer.
     *
     * <p>This test verifies that the method returns a 200 OK status when the transfer is successful,
     * and that the appropriate method is called on the mocked {@link TransferService}.
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: Mock the behavior of the {@link TransferService} to simulate a successful transfer.</li>
     *     <li>Act: Perform a POST request to the transfer endpoint with valid parameters.</li>
     *     <li>Assert: Verify that the response status is 200 OK and that the service method was called.</li>
     * </ol>
     *
     * @throws Exception if an error occurs during the test execution.
     */
    @Test
    void testTransferFunds_Success() throws Exception {
        // Arrange
        String fromAccount = "123456";
        String toAccount = "654321";
        BigDecimal amount = new BigDecimal("100.00");

        doNothing().when(transferService).transferFunds(fromAccount, toAccount, amount);

        // Act & Assert
        mockMvc.perform(post("/api/transfers")
                        .param("fromAccountNumber", fromAccount)
                        .param("toAccountNumber", toAccount)
                        .param("amount", amount.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify interactions
        verify(transferService, times(1)).transferFunds(fromAccount, toAccount, amount);
    }

    /**
     * Tests the {@link TransferController#transferFunds(String, String, BigDecimal)} method for a transfer failure.
     *
     * <p>This test verifies that the method returns a 400 Bad Request status when the transfer fails,
     * and that the appropriate method is called on the mocked {@link TransferService}.
     *
     * <p>Steps:
     * <ol>
     *     <li>Arrange: Mock the behavior of the {@link TransferService} to simulate a transfer failure.</li>
     *     <li>Act: Perform a POST request to the transfer endpoint with valid parameters.</li>
     *     <li>Assert: Verify that the response status is 400 Bad Request and that the service method was called.</li>
     * </ol>
     *
     * @throws Exception if an error occurs during the test execution.
     */
    @Test
    void testTransferFunds_Failure() throws Exception {
        // Arrange
        String fromAccount = "123456";
        String toAccount = "654321";
        BigDecimal amount = new BigDecimal("100.00");

        doThrow(new RuntimeException("Insufficient balance")).when(transferService).transferFunds(fromAccount, toAccount, amount);

        // Act & Assert
        mockMvc.perform(post("/api/transfers")
                        .param("fromAccountNumber", fromAccount)
                        .param("toAccountNumber", toAccount)
                        .param("amount", amount.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // Verify interactions
        verify(transferService, times(1)).transferFunds(fromAccount, toAccount, amount);
    }
}