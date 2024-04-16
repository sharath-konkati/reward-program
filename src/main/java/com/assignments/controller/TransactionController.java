package com.assignments.controller;

import com.assignments.dao.model.Transaction;
import com.assignments.exceptions.TransactionNotFoundException;
import com.assignments.repository.TransactionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;

    @Operation(summary = "Create a new transaction", description = "Create a new transaction record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transaction.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(transactionRepository.save(transaction));
    }

    @Operation(summary = "Update an existing transaction", description = "Update an existing transaction by providing transaction ID and updated details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transaction.class))}),
            @ApiResponse(responseCode = "404", description = "Transaction not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable Long id, @RequestBody Transaction updatedTransaction) {
        Transaction existingTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found"));
        if (updatedTransaction.getAmount() != null) {
            existingTransaction.setAmount(updatedTransaction.getAmount());
        }
        if (updatedTransaction.getTimestamp() != null) {
            existingTransaction.setTimestamp(updatedTransaction.getTimestamp());
        }
        return ResponseEntity.ok(transactionRepository.save(existingTransaction));
    }

    @Operation(summary = "Get a transaction", description = "Get a transaction record by transaction id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "transaction found with id"),
            @ApiResponse(responseCode = "404", description = "transaction not found with id")
    })
    public ResponseEntity<?> getTransaction(@PathVariable Long id) {
        return ResponseEntity.ok().body(transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found")));
    }

    @Operation(summary = "Delete a transaction", description = "Delete a transaction record by transaction id")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "transaction deleted by id"),
            @ApiResponse(responseCode = "404", description = "transaction not found to delete")
    })
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException("Transaction not found"));
        transactionRepository.deleteById(transaction.getId());
        return ResponseEntity.noContent().build();
    }
}
