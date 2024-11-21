package com.acme.nutrimove.platform;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Desactiva CSRF para desarrollo
                .authorizeRequests(auth -> auth.anyRequest().permitAll()); // Permite todas las solicitudes
        return http.build();
    }
}
