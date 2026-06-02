package com.airguitar.repository;

import com.airguitar.entity.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
    List<Instrument> findByOwnerId(Long ownerId);
}
