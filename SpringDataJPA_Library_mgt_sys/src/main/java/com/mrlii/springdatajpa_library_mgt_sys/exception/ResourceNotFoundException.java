package com.mrlii.springdatajpa_library_mgt_sys.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
