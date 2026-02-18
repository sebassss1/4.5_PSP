package com.example.demo.controladores;

import com.example.demo.entidades.Rol;
import com.example.demo.entidades.Usuario;
import com.example.demo.seguridad.JWTAuthenticationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.coyote.BadRequestException;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    JWTAuthenticationConfig jwtAuthtenticationConfig;

    // Repositorio de usuarios estático según la práctica
    private final List<Usuario> usuarios = List.of(
            new Usuario("aitor", "1234", Rol.ADMIN),
            new Usuario("ana", "5678", Rol.USER)
    );

    @PostMapping("/login")
    public String login(
            @RequestParam("user") String username,
            @RequestParam("encryptedPass") String encryptedPass)
            throws BadRequestException {

        // Buscamos al usuario en nuestra lista
        Usuario usuarioEncontrado = usuarios.stream()
                .filter(u -> u.getUsername().equals(username) && u.getEncryptedPass().equals(encryptedPass))
                .findFirst()
                .orElseThrow(() -> new BadRequestException("Usuario o contraseña incorrectos"));

        // Generamos el token pasando ahora también el ROL del usuario
        return jwtAuthtenticationConfig.getJWTToken(usuarioEncontrado.getUsername(), usuarioEncontrado.getRol().name());
    }
}