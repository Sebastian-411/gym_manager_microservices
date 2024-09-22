package com.icesi.edu.co.exception;

public class PaymentAlreadyExistsException extends RuntimeException {
    public PaymentAlreadyExistsException(String message) {
        super(message);
    }
}
