package com.assignments.controller;

import com.assignments.repository.TransactionRepository;
import com.assignments.dao.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction updatedTransaction) {
        Transaction existingTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (updatedTransaction.getAmount() != null) {
            existingTransaction.setAmount(updatedTransaction.getAmount());
        }
        if (updatedTransaction.getTimestamp() != null) {
            existingTransaction.setTimestamp(updatedTransaction.getTimestamp());
        }
        return transactionRepository.save(existingTransaction);
    }

    @GetMapping("/{id}")
    public Transaction getTransaction(@PathVariable Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionRepository.deleteById(id);
    }
}
