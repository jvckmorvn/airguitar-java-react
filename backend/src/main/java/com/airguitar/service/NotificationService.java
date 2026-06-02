package com.airguitar.service;

import com.airguitar.entity.Notification;
import com.airguitar.enums.NotificationType;
import com.airguitar.repository.NotificationRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(NotificationRepository notificationRepository, SimpMessagingTemplate messagingTemplate) {
        this.notificationRepository = notificationRepository;
        this.messagingTemplate = messagingTemplate;
    }

    public Notification createAndPush(Long userId, NotificationType type, String title, String body) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setType(type);
        notification.setTitle(title);
        notification.setBody(body);
        notification.setRead(false);
        notification.setCreatedAt(Instant.now());
        Notification saved = notificationRepository.save(notification);
        messagingTemplate.convertAndSend("/topic/users/" + userId + "/notifications", saved);
        return saved;
    }

    public List<Notification> myNotifications(Long userId) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public Notification markRead(Long id, Long userId) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Notification not found"));
        if (!notification.getUserId().equals(userId)) {
            throw new IllegalArgumentException("Cannot modify another user's notification");
        }
        notification.setRead(true);
        return notificationRepository.save(notification);
    }
}
