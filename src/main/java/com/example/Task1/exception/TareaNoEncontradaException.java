package com.example.Task1.exception;

public class TareaNoEncontradaException extends RuntimeException {
    public TareaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}
