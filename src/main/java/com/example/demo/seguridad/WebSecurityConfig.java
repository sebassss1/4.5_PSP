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
                // Deshabilitamos CSRF porque usaremos tokens JWT
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests(authz -> authz
                        // Permitimos el acceso libre al endpoint de login
                        .requestMatchers(HttpMethod.POST, Constans.LOGIN_URL).permitAll()
                        // Cualquier otra petición requiere que el usuario esté autenticado
                        .anyRequest().authenticated())
                // Añadimos nuestro filtro JWT después del filtro de autenticación estándar
                .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}