package com.airguitar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class InstrumentDtos {
    public record InstrumentRequest(
            @NotBlank String title,
            String description,
            @NotBlank String manufacturer,
            @NotBlank String model,
            @NotBlank String city,
            @NotBlank String country,
            @NotNull Double dailyRate,
            String imageUrls
    ) {}
}
