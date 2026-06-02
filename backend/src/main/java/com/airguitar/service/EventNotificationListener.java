package com.airguitar.service;

import com.airguitar.entity.Message;
import com.airguitar.enums.NotificationType;
import com.airguitar.event.BookingCreatedEvent;
import com.airguitar.event.MessageSentEvent;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class EventNotificationListener {
    private final NotificationService notificationService;
    private final SimpMessagingTemplate messagingTemplate;

    public EventNotificationListener(NotificationService notificationService, SimpMessagingTemplate messagingTemplate) {
        this.notificationService = notificationService;
        this.messagingTemplate = messagingTemplate;
    }

    @TransactionalEventListener
    public void onBookingCreated(BookingCreatedEvent event) {
        notificationService.createAndPush(
                event.instrument().getOwnerId(),
                NotificationType.BOOKING_CREATED,
                "New booking request",
                "Your instrument was booked from " + event.booking().getStartDate() + " to " + event.booking().getEndDate()
        );
    }

    @TransactionalEventListener
    public void onMessageSent(MessageSentEvent event) {
        Message message = event.message();
        notificationService.createAndPush(
                message.getReceiverId(),
                NotificationType.MESSAGE_RECEIVED,
                "New message",
                "You received a new message about instrument #" + message.getInstrumentId()
        );
        messagingTemplate.convertAndSend("/topic/users/" + message.getReceiverId() + "/messages", message);
    }
}
