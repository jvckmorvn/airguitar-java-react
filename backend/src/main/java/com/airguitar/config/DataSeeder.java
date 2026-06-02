package com.airguitar.config;

import com.airguitar.entity.Booking;
import com.airguitar.entity.Instrument;
import com.airguitar.entity.Message;
import com.airguitar.entity.Notification;
import com.airguitar.entity.User;
import com.airguitar.enums.NotificationType;
import com.airguitar.repository.BookingRepository;
import com.airguitar.repository.InstrumentRepository;
import com.airguitar.repository.MessageRepository;
import com.airguitar.repository.NotificationRepository;
import com.airguitar.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(
            UserRepository userRepository,
            InstrumentRepository instrumentRepository,
            BookingRepository bookingRepository,
            MessageRepository messageRepository,
            NotificationRepository notificationRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            if (userRepository.count() > 0) {
                return;
            }

            User owner = new User();
            owner.setName("Alex Owner");
            owner.setEmail("owner@airguitar.dev");
            owner.setPassword(passwordEncoder.encode("password123"));
            owner = userRepository.save(owner);

            User renter = new User();
            renter.setName("Riley Renter");
            renter.setEmail("renter@airguitar.dev");
            renter.setPassword(passwordEncoder.encode("password123"));
            renter = userRepository.save(renter);

            Instrument strat = new Instrument();
            strat.setTitle("Fender Stratocaster 2022");
            strat.setDescription("Excellent condition electric guitar with gig bag.");
            strat.setManufacturer("Fender");
            strat.setModel("Stratocaster");
            strat.setCity("Berlin");
            strat.setCountry("Germany");
            strat.setDailyRate(35.0);
            strat.setImageUrls("https://images.unsplash.com/photo-1511379938547-c1f69419868d");
            strat.setOwnerId(owner.getId());
            strat = instrumentRepository.save(strat);

            Instrument synth = new Instrument();
            synth.setTitle("Korg Minilogue XD");
            synth.setDescription("Analog synth for studio and live sessions.");
            synth.setManufacturer("Korg");
            synth.setModel("Minilogue XD");
            synth.setCity("Amsterdam");
            synth.setCountry("Netherlands");
            synth.setDailyRate(28.0);
            synth.setImageUrls("https://images.unsplash.com/photo-1516280440614-37939bbacd81");
            synth.setOwnerId(owner.getId());
            synth = instrumentRepository.save(synth);

            Booking booking = new Booking();
            booking.setInstrumentId(strat.getId());
            booking.setRenterId(renter.getId());
            booking.setStartDate(LocalDate.now().plusDays(3));
            booking.setEndDate(LocalDate.now().plusDays(5));
            booking.setTotalPrice(BigDecimal.valueOf(105.0));
            bookingRepository.save(booking);

            Message firstMessage = new Message();
            firstMessage.setConversationId(Math.min(owner.getId(), renter.getId()) + "_" + Math.max(owner.getId(), renter.getId()));
            firstMessage.setSenderId(renter.getId());
            firstMessage.setReceiverId(owner.getId());
            firstMessage.setInstrumentId(strat.getId());
            firstMessage.setContent("Hi! Is this available for pickup this weekend?");
            firstMessage.setTimestamp(Instant.now().minusSeconds(3600));
            messageRepository.save(firstMessage);

            Notification bookingNotification = new Notification();
            bookingNotification.setUserId(owner.getId());
            bookingNotification.setType(NotificationType.BOOKING_CREATED);
            bookingNotification.setTitle("Sample booking created");
            bookingNotification.setBody("Riley requested your Fender Stratocaster.");
            bookingNotification.setRead(false);
            bookingNotification.setCreatedAt(Instant.now().minusSeconds(1800));
            notificationRepository.save(bookingNotification);

            Notification messageNotification = new Notification();
            messageNotification.setUserId(owner.getId());
            messageNotification.setType(NotificationType.MESSAGE_RECEIVED);
            messageNotification.setTitle("Sample message received");
            messageNotification.setBody("You have a new message about Fender Stratocaster 2022.");
            messageNotification.setRead(false);
            messageNotification.setCreatedAt(Instant.now().minusSeconds(1200));
            notificationRepository.save(messageNotification);
        };
    }
}
