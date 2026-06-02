package com.airguitar.controller;

import com.airguitar.entity.Notification;
import com.airguitar.security.AirguitarPrincipal;
import com.airguitar.service.NotificationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/my")
    public List<Notification> myNotifications(@AuthenticationPrincipal AirguitarPrincipal principal) {
        return notificationService.myNotifications(principal.userId());
    }

    @PutMapping("/{id}/read")
    public Notification markRead(@PathVariable Long id, @AuthenticationPrincipal AirguitarPrincipal principal) {
        return notificationService.markRead(id, principal.userId());
    }
}
