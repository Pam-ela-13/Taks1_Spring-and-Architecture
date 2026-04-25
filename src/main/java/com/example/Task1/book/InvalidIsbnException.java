package com.example.Task1.book;

public class InvalidIsbnException extends RuntimeException {
    public InvalidIsbnException(String mensaje) {
        super(mensaje);
    }
}
