package com.airguitar.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Guitar {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @Column(nullable = false)
    public String model;

    @Column(nullable = false)
    public String manufacturer;

}
