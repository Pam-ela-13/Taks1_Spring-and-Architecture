package com.example.Task1.book;

/**
 * BookModel - SIMPLE
 * Entidad que representa un libro
 */
public class BookModel {
    
    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private Integer paginas;
    private String genero;
    
    public BookModel() {}
    
    public BookModel(Long id, String titulo, String autor, String isbn, 
                    Integer paginas, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.paginas = paginas;
        this.genero = genero;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    
    public Integer getPaginas() { return paginas; }
    public void setPaginas(Integer paginas) { this.paginas = paginas; }
    
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
}

