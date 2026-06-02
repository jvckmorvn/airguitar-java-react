package com.airguitar.event;

import com.airguitar.entity.Booking;
import com.airguitar.entity.Instrument;

public record BookingCreatedEvent(Booking booking, Instrument instrument) {}
