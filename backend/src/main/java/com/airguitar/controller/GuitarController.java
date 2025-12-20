package com.airguitar.controller;

import com.example.airguitar.model.dto.GuitarDTO;
import com.example.airguitar.service.GuitarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/guitars")
@RequiredArgsConstructor
public class GuitarController {

    private final GuitarService guitarService;

    @GetMapping
    public List<GuitarDTO> index() {
        return guitarService.findAll();
    }

    @GetMapping("/{id}")
    public GuitarDTO show(@PathVariable Long id) { return guitarService.findById(id); }

}
