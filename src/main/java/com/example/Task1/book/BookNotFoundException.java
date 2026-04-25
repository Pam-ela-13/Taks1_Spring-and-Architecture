package com.example.Task1.book;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String mensaje) {
        super(mensaje);
    }
}
