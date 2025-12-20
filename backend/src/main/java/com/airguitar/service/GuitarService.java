package com.airguitar.service;

import com.example.airguitar.mapper.GuitarMapper;
import com.example.airguitar.model.dto.GuitarDTO;
import com.example.airguitar.repository.GuitarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuitarService {

    private final GuitarRepository guitarRepository;
    private final GuitarMapper guitarMapper;

    public List<GuitarDTO> findAll() {
        return guitarRepository.findAll()
            .stream()
            .map(guitarMapper::toDto)
            .toList();
    }

    public GuitarDTO findById(Long id) {
        return guitarRepository.findById(id)
            .map(guitarMapper::toDto)
            .orElse(null);
    }

}
