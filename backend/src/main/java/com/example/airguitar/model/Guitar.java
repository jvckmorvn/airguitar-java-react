package com.example.airguitar.model;

import jakarta.persistence.*;

@Entity
public class Guitar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String model;
}
