package com.example.demo.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Autowired
    JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                // Deshabilitamos CSRF porque para APIs basadas en tokens JWT no es necesario
                .csrf((csrf) -> csrf.disable())

                .authorizeHttpRequests(authz -> authz
                        // 1. Permitimos que cualquier persona (sin token) acceda al /login
                        .requestMatchers(HttpMethod.POST, Constans.LOGIN_URL).permitAll()

                        // 2. REGLAS DE AUTORIZACIÓN POR ROL (Parte 2 de la práctica)

                        // Lectura: Tanto el ADMIN como el USER pueden ver la lista de contactos
                        .requestMatchers(HttpMethod.GET, "/contactos/**").hasAnyRole("ADMIN", "USER")

                        // Escritura: SOLO el usuario con rol ADMIN puede crear nuevos contactos
                        .requestMatchers(HttpMethod.POST, "/contactos/**").hasRole("ADMIN")

                        // Modificación: SOLO el ADMIN puede actualizar contactos existentes
                        .requestMatchers(HttpMethod.PUT, "/contactos/**").hasRole("ADMIN")

                        // Eliminación: SOLO el ADMIN tiene permiso para borrar contactos
                        .requestMatchers(HttpMethod.DELETE, "/contactos/**").hasRole("ADMIN")

                        // 3. Bloqueo de seguridad: cualquier otra ruta no especificada requiere autenticación
                        .anyRequest().authenticated())

                // Registramos nuestro filtro personalizado (JWTAuthorizationFilter)
                // para que se ejecute después del filtro de autenticación de Spring.
                .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}