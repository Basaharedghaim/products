package com.events.products.business.exception;

public class StoreAlreadyExistsException extends RuntimeException {
    public StoreAlreadyExistsException(String message) {
        super(message);
    }
}
