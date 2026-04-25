package com.example.Task1.book;

public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException(String mensaje) {
        super(mensaje);
    }
}
