package com.projectvault.projectvaultbackend.common.exception;

public class DataAlreadyExistException extends RuntimeException {
    public DataAlreadyExistException(String message) {
        super(message);
    }
}
