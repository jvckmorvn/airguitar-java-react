package com.airguitar.websocket;

import com.airguitar.dto.MessageDtos;
import com.airguitar.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWebSocketController {
    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatWebSocketController(MessageService messageService, SimpMessagingTemplate messagingTemplate) {
        this.messageService = messageService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.send")
    public void send(@Valid @Payload MessageDtos.SendMessagePayload payload) {
        MessageDtos.MessageResponse saved = messageService.sendMessage(payload);
        String topic = "/topic/chat/" + payload.instrumentId() + "/" + messageService.conversationId(payload.senderId(), payload.receiverId());
        messagingTemplate.convertAndSend(topic, saved);
    }
}
