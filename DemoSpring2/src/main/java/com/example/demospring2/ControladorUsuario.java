package com.example.demospring2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

@RestController
public class ControladorUsuario {

    @PostMapping("/form-usuario")
    public ResponseEntity<Usuario> formUsuario(@RequestParam String nickname,
                                              @RequestParam String password) {
        Usuario usuario = new Usuario(nickname, password);

        System.out.println("Bieeeen");
        return ResponseEntity.ok(usuario);

    }
}
