package com.airguitar.repository;

import com.airguitar.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByRenterId(Long renterId);

    List<Booking> findByInstrumentIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Long instrumentId, LocalDate endDate, LocalDate startDate
    );
}
