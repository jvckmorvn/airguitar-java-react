package com.airguitar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public class MessageDtos {
    public record SendMessagePayload(@NotNull Long senderId, @NotNull Long receiverId, @NotNull Long instrumentId, @NotBlank String content) {}
    public record MessageResponse(Long id, String conversationId, Long senderId, Long receiverId, Long instrumentId, String content, Instant timestamp) {}
}
