package com.example.Task1.exception;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorResponse {
    private int codigo;
    private String mensaje;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
    
    public ErrorResponse(int codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.timestamp = LocalDateTime.now();
    }
    
    public static ErrorResponse of(int codigo, String mensaje) {
        return new ErrorResponse(codigo, mensaje);
    }
    
    public int getCodigo() {
        return codigo;
    }
    
    public String getMensaje() {
        return mensaje;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
