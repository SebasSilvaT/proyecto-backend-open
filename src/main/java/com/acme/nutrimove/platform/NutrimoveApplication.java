package com.acme.nutrimove.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class NutrimoveApplication {

    public static void main(String[] args) {
        SpringApplication.run(NutrimoveApplication.class, args);
    }

    @Configuration
    public static class WebConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/api/v1/**") // Endpoints que permiten CORS
                    .allowedOrigins(
                            "http://673e677494f3a8000828c568--nutrimove.netlify.app", // Dominio del frontend
                            "https://nutrimove.up.railway.app" // Dominio del backend para pruebas
                    )
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                    .allowedHeaders("*") // Permitir todas las cabeceras
                    .allowCredentials(true); // Para manejo de sesiones o cookies
        }
    }
}
