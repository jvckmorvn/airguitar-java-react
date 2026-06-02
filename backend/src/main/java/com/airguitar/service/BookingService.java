package com.airguitar.service;

import com.airguitar.dto.BookingDtos;
import com.airguitar.entity.Booking;
import com.airguitar.entity.Instrument;
import com.airguitar.event.BookingCreatedEvent;
import com.airguitar.repository.BookingRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final InstrumentService instrumentService;
    private final ApplicationEventPublisher eventPublisher;

    public BookingService(BookingRepository bookingRepository, InstrumentService instrumentService, ApplicationEventPublisher eventPublisher) {
        this.bookingRepository = bookingRepository;
        this.instrumentService = instrumentService;
        this.eventPublisher = eventPublisher;
    }

    public Booking createBooking(BookingDtos.BookingRequest request, Long renterId) {
        if (request.endDate().isBefore(request.startDate())) {
            throw new IllegalArgumentException("endDate must be after startDate");
        }

        Instrument instrument = instrumentService.findById(request.instrumentId());
        if (instrument.getOwnerId().equals(renterId)) {
            throw new IllegalArgumentException("Owner cannot book their own instrument");
        }

        boolean overlap = !bookingRepository.findByInstrumentIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                request.instrumentId(), request.endDate(), request.startDate()
        ).isEmpty();
        if (overlap) {
            throw new IllegalArgumentException("Booking overlaps existing reservation");
        }

        long days = ChronoUnit.DAYS.between(request.startDate(), request.endDate()) + 1;
        Booking booking = new Booking();
        booking.setInstrumentId(request.instrumentId());
        booking.setRenterId(renterId);
        booking.setStartDate(request.startDate());
        booking.setEndDate(request.endDate());
        booking.setTotalPrice(BigDecimal.valueOf(instrument.getDailyRate() * days));

        Booking saved = bookingRepository.save(booking);
        eventPublisher.publishEvent(new BookingCreatedEvent(saved, instrument));
        return saved;
    }

    public List<Booking> getMyBookings(Long userId) {
        return bookingRepository.findByRenterId(userId);
    }
}
