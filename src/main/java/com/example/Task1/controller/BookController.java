package com.example.Task1.controller;

import com.example.Task1.book.BookModel;
import com.example.Task1.book.BookService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.List;

/**
 * BookController - SIMPLE
 * Demuestra DEPENDENCY INJECTION (IoC)
 */
@RestController
@RequestMapping("/api/libros")
public class BookController {
    
    // ✓ Inyección de dependencias por Constructor
    private final BookService bookService;
    
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    // GET - Obtener todos los libros (200 OK)
    @GetMapping
    public ResponseEntity<Map<String, Object>> obtenerTodos() {
        List<BookModel> libros = bookService.obtenerTodos();
        return ResponseEntity.ok(Map.of(
            "status", "200",
            "total", libros.size(),
            "libros", libros
        ));
    }
    
    // GET - Obtener un libro (200 OK o 404 NOT FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerPorId(@PathVariable Long id) {
        BookModel libro = bookService.obtenerPorId(id);
        return ResponseEntity.ok(Map.of(
            "status", "200",
            "libro", libro
        ));
    }
    
    // POST - Crear un libro (200 OK o 404)
    @PostMapping
    public ResponseEntity<Map<String, Object>> crearLibro(@RequestBody BookModel libro) {
        BookModel libroCreado = bookService.crearLibro(libro);
        return ResponseEntity.ok(Map.of(
            "status", "200",
            "libro", libroCreado
        ));
    }
}
