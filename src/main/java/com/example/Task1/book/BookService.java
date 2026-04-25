package com.example.Task1.book;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

/**
 * BookService - SIMPLE
 * 
 */
@Service
public class BookService {
    
    private static final Logger log = LoggerFactory.getLogger(BookService.class);
    
    private final Map<Long, BookModel> libros = new HashMap<>();
    
    public BookService() {
        libros.put(1L, new BookModel(1L, "El Quijote", "Cervantes", "ISBN-001", 1072, "Novela"));
        libros.put(2L, new BookModel(2L, "1984", "Orwell", "ISBN-002", 328, "Distopía"));
    }
    
    // GET - Obtener todos
    public List<BookModel> obtenerTodos() {
        return new ArrayList<>(libros.values());
    }
    
    // GET - Obtener por ID
    public BookModel obtenerPorId(Long id) {
        BookModel libro = libros.get(id);
        if (libro == null) {
            throw new BookNotFoundException("Libro no encontrado");
        }
        return libro;
    }
    
    // POST - Crear
    public BookModel crearLibro(BookModel libro) {
        Long id = (long) (libros.size() + 1);
        libro.setId(id);
        libros.put(id, libro);
        return libro;
    }
}
