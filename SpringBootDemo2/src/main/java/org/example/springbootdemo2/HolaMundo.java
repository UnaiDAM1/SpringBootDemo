package org.example.springbootdemo2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaMundo {
    @GetMapping("/hola")
    public String hola() {
        return "Hola Mundo!!!";
    }
}