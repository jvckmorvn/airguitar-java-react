package com.airguitar.service;

import com.airguitar.dto.InstrumentDtos;
import com.airguitar.entity.Instrument;
import com.airguitar.repository.InstrumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstrumentService {
    private final InstrumentRepository instrumentRepository;

    public InstrumentService(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    public List<Instrument> findAll() {
        return instrumentRepository.findAll();
    }

    public Instrument findById(Long id) {
        return instrumentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Instrument not found"));
    }

    public Instrument create(InstrumentDtos.InstrumentRequest request, Long ownerId) {
        Instrument instrument = new Instrument();
        map(instrument, request);
        instrument.setOwnerId(ownerId);
        return instrumentRepository.save(instrument);
    }

    public Instrument update(Long id, InstrumentDtos.InstrumentRequest request, Long userId) {
        Instrument instrument = findById(id);
        if (!instrument.getOwnerId().equals(userId)) {
            throw new IllegalArgumentException("Only owner can update instrument");
        }
        map(instrument, request);
        return instrumentRepository.save(instrument);
    }

    public void delete(Long id, Long userId) {
        Instrument instrument = findById(id);
        if (!instrument.getOwnerId().equals(userId)) {
            throw new IllegalArgumentException("Only owner can delete instrument");
        }
        instrumentRepository.deleteById(id);
    }

    private void map(Instrument instrument, InstrumentDtos.InstrumentRequest request) {
        instrument.setTitle(request.title());
        instrument.setDescription(request.description());
        instrument.setManufacturer(request.manufacturer());
        instrument.setModel(request.model());
        instrument.setCity(request.city());
        instrument.setCountry(request.country());
        instrument.setDailyRate(request.dailyRate());
        instrument.setImageUrls(request.imageUrls());
    }
}
