package com.airguitar.service;

import com.airguitar.mapper.GuitarMapper;
import com.airguitar.model.dto.GuitarDTO;
import com.airguitar.repository.GuitarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        if (ids == null || ids.isEmpty()) {
            return findAll();
        }

        List<UUID> uuids = ids.stream()
            .map(UUID::fromString)
            .toList();

        return guitarRepository.findAllById(uuids)
            .stream()
            .map(guitarMapper::toDto)
            .toList();
    }

    public Optional<GuitarDTO> findById(String id) {
        UUID uuid = UUID.fromString(id);

        return guitarRepository.findById(uuid)
            .map(guitarMapper::toDto);
    }

}
