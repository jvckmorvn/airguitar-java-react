package com.airguitar.service;

import com.airguitar.mapper.GuitarMapper;
import com.airguitar.model.dto.GuitarDTO;
import com.airguitar.repository.GuitarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<GuitarDTO> findByIds(List<String> ids) {
        return guitarRepository.findAllById(ids)
            .stream()
            .map(guitarMapper::toDto)
            .toList();
    }

    public Optional<GuitarDTO> findById(String id) {
        return guitarRepository.findById(id)
            .map(guitarMapper::toDto);
    }
}
