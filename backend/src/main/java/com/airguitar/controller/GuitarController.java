package com.airguitar.controller;

import com.airguitar.model.dto.GuitarDTO;
import com.airguitar.service.GuitarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guitars")
@RequiredArgsConstructor
public class GuitarController {

    private final GuitarService guitarService;

    @GetMapping
    public List<GuitarDTO> index(@RequestParam List<String> ids) { return guitarService.findByIds(ids); }

    @GetMapping("/{id}")
    public GuitarDTO show(@PathVariable String id) { return guitarService.findById(id); }

}
