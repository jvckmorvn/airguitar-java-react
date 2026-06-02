package com.airguitar.controller;

import com.airguitar.dto.BookingDtos;
import com.airguitar.entity.Booking;
import com.airguitar.security.AirguitarPrincipal;
import com.airguitar.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking create(@Valid @RequestBody BookingDtos.BookingRequest request, @AuthenticationPrincipal AirguitarPrincipal principal) {
        return bookingService.createBooking(request, principal.userId());
    }

    @GetMapping("/my")
    public List<Booking> myBookings(@AuthenticationPrincipal AirguitarPrincipal principal) {
        return bookingService.getMyBookings(principal.userId());
    }
}
