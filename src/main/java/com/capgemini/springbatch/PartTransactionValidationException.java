package com.capgemini.springbatch;

public class PartTransactionValidationException extends RuntimeException {

    public PartTransactionValidationException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
