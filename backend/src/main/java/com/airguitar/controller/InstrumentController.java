package com.airguitar.controller;

import com.airguitar.dto.InstrumentDtos;
import com.airguitar.entity.Instrument;
import com.airguitar.security.AirguitarPrincipal;
import com.airguitar.service.InstrumentService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/instruments")
public class InstrumentController {
    private final InstrumentService instrumentService;

    public InstrumentController(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @GetMapping
    public List<Instrument> all() {
        return instrumentService.findAll();
    }

    @GetMapping("/{id}")
    public Instrument getById(@PathVariable Long id) {
        return instrumentService.findById(id);
    }

    @PostMapping
    public Instrument create(@Valid @RequestBody InstrumentDtos.InstrumentRequest request, @AuthenticationPrincipal AirguitarPrincipal principal) {
        return instrumentService.create(request, principal.userId());
    }

    @PutMapping("/{id}")
    public Instrument update(@PathVariable Long id, @Valid @RequestBody InstrumentDtos.InstrumentRequest request, @AuthenticationPrincipal AirguitarPrincipal principal) {
        return instrumentService.update(id, request, principal.userId());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, @AuthenticationPrincipal AirguitarPrincipal principal) {
        instrumentService.delete(id, principal.userId());
    }
}
