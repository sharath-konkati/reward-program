package com.assignments.exceptions;

import lombok.Getter;

@Getter
public class TransactionNotFoundException extends RuntimeException {
    private String message;

    public TransactionNotFoundException(String message) {
        super(message);
        this.message = message;
    }

}
