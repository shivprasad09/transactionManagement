package com.example.onlinebanking.controller;

import com.example.onlinebanking.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * REST controller for handling fund transfer operations.
 * This class exposes an endpoint for transferring funds between two accounts.
 * It interacts with the {@link TransferService} to perform the business logic for fund transfers.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 */
@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    @Autowired
    private TransferService transferService;

    /**
     * Transfers funds from one account to another.
     * This endpoint accepts the source account number, destination account number, and the amount to transfer as request parameters.
     * It delegates the transfer operation to the {@link TransferService}.
     *
     * @param fromAccountNumber the account number of the source account, provided as a request parameter.
     * @param toAccountNumber the account number of the destination account, provided as a request parameter.
     * @param amount the amount to transfer, provided as a request parameter.
     * @throws RuntimeException if the source or destination account is not found, or if the source account has insufficient balance.
     */
    @PostMapping
    public void transferFunds(@RequestParam String fromAccountNumber,
                              @RequestParam String toAccountNumber,
                              @RequestParam BigDecimal amount) {
        transferService.transferFunds(fromAccountNumber, toAccountNumber, amount);
    }
}