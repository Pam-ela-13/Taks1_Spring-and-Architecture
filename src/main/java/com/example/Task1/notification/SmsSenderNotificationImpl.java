package com.example.Task1.notification;

import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class SmsSenderNotificationImpl implements NotificationInterface {
    
    private static final Logger log = LoggerFactory.getLogger(
        SmsSenderNotificationImpl.class);
    
    @Override
    public void enviarNotificacion(String destinatario, String mensaje) {
        log.info(" Enviando SMS a {} con mensaje: {}", 
                 destinatario, mensaje);
    }
    
    @Override
    public String obtenerTipo() {
        return "SMS";
    }
}
