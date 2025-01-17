package com.example.demospring2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
public class HolaMundo {
    @GetMapping("/holiii")
    public String hola() {
        return "Hola Mundo";
    }

    @GetMapping("/adiooos")
    public String despedida() {
        return "Hastaa la proximaaa";
    }

    @GetMapping("/lafecha")
    public LocalDate quefecha() {
        return LocalDate.now();
    }

    @GetMapping("/lahora")
    public LocalDateTime hora() {
        return LocalDateTime.now();
    }

    @PostMapping("/login")
    public String login(@RequestParam String nickname, @RequestParam String password, @RequestParam int edad) {
        String mensaje = "";
        if (edad<18)
        {
            mensaje = "PEQUEÑOOOOOO";
        }
        else {
            mensaje = "Info recibida nickname = " + nickname + " y la password = " + password + " eres mayor";
        }
        return mensaje;
    }

    @PostMapping("/form-libro")
    public ResponseEntity<Libro> postLibro(@RequestParam String isbn,
                                           @RequestParam String titulo,
                                           @RequestParam String autor,
                                           @RequestParam String password){
        Libro l = new Libro(isbn, titulo, autor);
        return ResponseEntity.ok(l);
    }

}
