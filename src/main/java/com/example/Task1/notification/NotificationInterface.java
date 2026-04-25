package com.example.Task1.notification;

public interface NotificationInterface {
    void enviarNotificacion(String destinatario, String mensaje);
    String obtenerTipo();
}
