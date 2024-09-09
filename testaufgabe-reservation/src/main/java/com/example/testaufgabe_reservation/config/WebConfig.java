package com.example.testaufgabe_reservation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Erlaubt CORS für alle Endpunkte
                .allowedOrigins("http://localhost:3000")  // Erlaubt Anfragen von deinem React-Frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Erlaubt diese HTTP-Methoden
                .allowedHeaders("*")  // Erlaubt alle Header
                .allowCredentials(true);  // Erlaubt Cookies und Authentifizierungsheader
    }
}