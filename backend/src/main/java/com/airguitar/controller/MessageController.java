package com.airguitar.controller;

import com.airguitar.dto.MessageDtos;
import com.airguitar.security.AirguitarPrincipal;
import com.airguitar.service.MessageService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/{instrumentId}/{userId}")
    public List<MessageDtos.MessageResponse> history(
            @PathVariable Long instrumentId,
            @PathVariable Long userId,
            @AuthenticationPrincipal AirguitarPrincipal principal
    ) {
        return messageService.getHistory(instrumentId, principal.userId(), userId);
    }
}
