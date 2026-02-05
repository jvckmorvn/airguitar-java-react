package com.airguitar.repository;

import com.airguitar.model.Guitar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GuitarRepository extends JpaRepository<Guitar, UUID> {}
