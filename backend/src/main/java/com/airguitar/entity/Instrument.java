package com.airguitar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "instruments")
public class Instrument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private Double dailyRate;

    @Column(columnDefinition = "TEXT")
    private String imageUrls;

    @Column(nullable = false)
    private Long ownerId;

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getManufacturer() { return manufacturer; }
    public String getModel() { return model; }
    public String getCity() { return city; }
    public String getCountry() { return country; }
    public Double getDailyRate() { return dailyRate; }
    public String getImageUrls() { return imageUrls; }
    public Long getOwnerId() { return ownerId; }
    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }
    public void setModel(String model) { this.model = model; }
    public void setCity(String city) { this.city = city; }
    public void setCountry(String country) { this.country = country; }
    public void setDailyRate(Double dailyRate) { this.dailyRate = dailyRate; }
    public void setImageUrls(String imageUrls) { this.imageUrls = imageUrls; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
}
