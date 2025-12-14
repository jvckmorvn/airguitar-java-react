package com.example.airguitar.service;

import com.example.airguitar.model.Guitar;
import com.example.airguitar.repository.GuitarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuitarService {

    private final GuitarRepository guitarRepository;

    public List<Guitar> getAll() {
        return guitarRepository.findAll();
    }

}
