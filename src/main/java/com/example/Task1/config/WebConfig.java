package com.example.Task1.config;

import com.example.Task1.interceptor.ApiKeyInterceptor;
import com.example.Task1.interceptor.LoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * WebConfig - Configuración de Interceptadores en Spring
 * 
 * Los interceptadores se ejecutan en el siguiente orden:
 * 1. ApiKeyInterceptor.preHandle()      → Valida API Key (AUTENTICACIÓN)
 * 2. LoggingInterceptor.preHandle()     → Registra entrada (LOGGING)
 * 3. [Controller procesa request]
 * 4. LoggingInterceptor.postHandle()    → Registra salida (LOGGING)
 * 5. LoggingInterceptor.afterCompletion()→ Mide tiempo (PERFORMANCE)
 * 
 * Las tres clases de uso de interceptadores:
 * - AUTHENTICATION (Seguridad): ApiKeyInterceptor valida credenciales
 * - LOGGING (Auditoría): LoggingInterceptor registra operaciones
 * - PERFORMANCE (Monitoreo): afterCompletion mide tiempo de respuesta
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    private static final Logger log = LoggerFactory.getLogger(WebConfig.class);
    
    private final ApiKeyInterceptor apiKeyInterceptor;
    private final LoggingInterceptor loggingInterceptor;
    
    /**
     * Constructor - Inyección de dependencias de los interceptadores
     * Spring automáticamente inyecta los beans de los interceptadores
     */
    public WebConfig(ApiKeyInterceptor apiKeyInterceptor,
                     LoggingInterceptor loggingInterceptor) {
        this.apiKeyInterceptor = apiKeyInterceptor;
        this.loggingInterceptor = loggingInterceptor;
        log.info("✓ WebConfig inicializado con interceptadores");
    }
    
    /**
     * addInterceptors - Registra los interceptadores
     * 
     * Este método es llamado por Spring al iniciar la aplicación.
     * Aquí se define:
     * - Qué interceptador se usa
     * - A qué rutas se aplica (addPathPatterns)
     * - De qué rutas se excluye (excludePathPatterns)
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        
        log.info("📋 Registrando interceptadores...");
        
        // ========== INTERCEPTADOR 1: AUTHENTICATION ==========
        // Propósito: Validar API Key en todas las solicitudes a /api/**
        registry.addInterceptor(apiKeyInterceptor)
                // ✓ Se aplica a todas las rutas /api/**
                .addPathPatterns("/api/**")
                // ❌ Se excluye de /hello/** (rutas públicas)
                .excludePathPatterns("/hello/**");
        
        log.info("✓ ApiKeyInterceptor registrado para /api/**");
        
        // ========== INTERCEPTADOR 2: LOGGING + PERFORMANCE ==========
        // Propósito: Registrar todas las solicitudes y medir tiempo
        registry.addInterceptor(loggingInterceptor)
                // ✓ Se aplica a TODAS las rutas
                .addPathPatterns("/**");
        
        log.info("✓ LoggingInterceptor registrado para /**");
        
        // NOTA: El orden de registro es importante.
        // Los interceptadores se ejecutan en el orden en que se registran.
    }
    
    /**
     * Orden de ejecución para una solicitud a /api/libros/1:
     * 
     * REQUEST ENTRADA:
     * 1. ApiKeyInterceptor.preHandle()          ← Autentica
     * 2. LoggingInterceptor.preHandle()         ← Registra inicio
     * 
     * PROCESAMIENTO:
     * 3. BookController.obtenerPorId(1)         ← Controller ejecuta lógica
     * 
     * RESPUESTA SALIDA:
     * 4. LoggingInterceptor.postHandle()        ← Registra respuesta
     * 5. LoggingInterceptor.afterCompletion()   ← Calcula tiempo total
     * 
     * Si ocurre excepción en postHandle/afterCompletion, se ejecutan igualmente.
     */
}
