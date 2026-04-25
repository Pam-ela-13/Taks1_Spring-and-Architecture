package com.example.Task1.exception;

public class ApiKeyInvalidaException extends RuntimeException {
    public ApiKeyInvalidaException(String mensaje) {
        super(mensaje);
    }
}
