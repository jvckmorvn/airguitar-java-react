package com.airguitar.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class BookingDtos {
    public record BookingRequest(@NotNull Long instrumentId, @NotNull LocalDate startDate, @NotNull LocalDate endDate) {}
}
