package com.boot.amazon.exception;

public class NoReviewException extends RuntimeException {
    public NoReviewException(String message) {
        super(message);
    }
}
