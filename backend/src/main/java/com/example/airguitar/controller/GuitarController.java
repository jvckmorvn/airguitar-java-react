package com.example.airguitar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuitarController {

    @GetMapping("/guitars")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
