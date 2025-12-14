package com.example.airguitar.controller;

import com.example.airguitar.model.Guitar;
import com.example.airguitar.service.GuitarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GuitarController {

    private final GuitarService guitarService;

    @GetMapping("/guitars")
    public List<Guitar> index() {
        return guitarService.getAll();
    }

}
