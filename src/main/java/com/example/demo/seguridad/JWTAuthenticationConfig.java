package com.example.demo.seguridad;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.seguridad.Constans.*;

@Configuration
public class JWTAuthenticationConfig {

    public String getJWTToken(String username) {
        // 1. Definimos los roles del usuario (en este caso, ROLE_USER)
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        // 2. Construimos el token con sus propiedades [cite: 139, 140]
        String token = Jwts
                .builder()
                .setId("espinozajgeJWT") // ID único del token [cite: 151]
                .setSubject(username)    // El nombre del usuario [cite: 152]
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList())) // Añadimos los roles [cite: 153, 157]
                .setIssuedAt(new Date(System.currentTimeMillis())) // Fecha de creación [cite: 156]
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME)) // 10 días [cite: 156, 158]
                .signWith(getSigningKey(SUPER_SECRET_KEY), SignatureAlgorithm.HS512) // Firma [cite: 141, 160]
                .compact(); // Comprimir en cadena de texto [cite: 141]

        // 3. Retornamos el token con el prefijo estándar [cite: 142, 161]
        return TOKEN_BEARER_PREFIX + token;
    }
}