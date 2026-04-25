package com.example.Task1.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * LoggingInterceptor - Demuestra dos casos de uso:
 * 
 * 1. LOGGING (Auditoría)
 *    - Registra detalles de cada request
 *    - Registra detalles de cada response
 *    - Permite auditar quién accedió qué y cuándo
 * 
 * 2. PERFORMANCE (Monitoreo)
 *    - Mide tiempo total de ejecución
 *    - Ayuda a detectar endpoints lentos
 *    - Permite optimizar la aplicación
 */
@Component
public class LoggingInterceptor implements HandlerInterceptor {
    
    private static final Logger log = LoggerFactory.getLogger(
        LoggingInterceptor.class);
    
    /**
     * preHandle - Ejecuta ANTES que el controller
     * Propósito: LOGGING (Auditoría)
     * 
     * Se ejecuta:
     * - Antes que el controller procese la solicitud
     * - Se registra el REQUEST
     * - Se inicia el timer de performance
     * 
     * @return true → Continúa al siguiente interceptador/controller
     *         false → Detiene la ejecución
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        
        // ========== USE CASE 1: LOGGING ==========
        log.info("━━━ ENTRADA REQUEST ━━━");
        log.info(" Método: {}", request.getMethod());
        log.info(" URI: {}", request.getRequestURI());
        log.info(" IP Cliente: {}", request.getRemoteAddr());
        log.info(" Query Params: {}", request.getQueryString());
        
        // ========== USE CASE 2: PERFORMANCE - START TIMER ==========
        long inicioMs = System.currentTimeMillis();
        request.setAttribute("tiempoInicio", inicioMs);
        log.info("⏱️  Inicio: {} ms", inicioMs);
        
        return true;  // Continúa con el siguiente interceptador
    }
    
    /**
     * postHandle - Ejecuta DESPUÉS que el controller
     * Propósito: LOGGING (Auditoría de respuesta)
     * 
     * Se ejecuta:
     * - Después que el controller procesa
     * - Antes de que Spring envíe la respuesta al cliente
     * - Se registra la RESPONSE
     * 
     * NOTA: Si ocurre excepción en el controller, NO se ejecuta
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) {
        
        // ========== USE CASE 1: LOGGING ==========
        log.info("━━━ SALIDA RESPONSE ━━━");
        log.info(" Status Code: {}", response.getStatus());
        log.info(" Content-Type: {}", response.getContentType());
        log.info(" Headers: Cache-Control = {}", 
                response.getHeader("Cache-Control"));
    }
    
    /**
     * afterCompletion - Ejecuta AL FINAL de TODO
     * Propósito: LOGGING final + PERFORMANCE metrics
     * 
     * Se ejecuta:
     * - Después que spring ha enviado la respuesta al cliente
     * - Incluso si ocurre excepción en el controller
     * - Es el lugar IDEAL para cleanup y métricas
     * 
     * @param ex - Excepción ocurrida (null si no hay error)
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                               HttpServletResponse response,
                               Object handler,
                               Exception ex) {
        
        // ========== USE CASE 2: PERFORMANCE - CALCULATE TIME ==========
        Long inicioMs = (Long) request.getAttribute("tiempoInicio");
        long finMs = System.currentTimeMillis();
        long tiempoTotal = finMs - inicioMs;
        
        log.info("━━━ MÉTRICAS ━━━");
        log.info("  Tiempo total: {} ms", tiempoTotal);
        log.info("  Fin: {} ms", finMs);
        
        // Alertar si es lento
        if (tiempoTotal > 1000) {
            log.warn(" REQUEST LENTO: {} ms para {}", 
                    tiempoTotal, request.getRequestURI());
        }
        
        // Logging de excepciones
        if (ex != null) {
            log.error(" EXCEPCIÓN: {}", ex.getMessage());
        } else {
            log.info(" Completado exitosamente");
        }
        
        log.info("━━━━━━━━━━━━━━━━━━━\n");  // Separador visual
    }
}
