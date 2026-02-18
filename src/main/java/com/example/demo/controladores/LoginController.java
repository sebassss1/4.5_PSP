package com.example.demo.controladores;

import com.example.demo.seguridad.Constans;
import com.example.demo.seguridad.JWTAuthenticationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.coyote.BadRequestException;

@RestController
public class LoginController {

    @Autowired
    JWTAuthenticationConfig jwtAuthtenticationConfig;

    @PostMapping("/login")
    public String login(
            @RequestParam("user") String username,
            @RequestParam("encryptedPass") String encryptedPass)
            throws BadRequestException {

        if (!username.equals(Constans.USER) || !encryptedPass.equals(Constans.PASS)) {
            throw new BadRequestException("Usuario o contraseña incorrectos");
        }

        return jwtAuthtenticationConfig.getJWTToken(username);
    }
}