package com.airguitar.service;

import com.airguitar.dto.MessageDtos;
import com.airguitar.entity.Message;
import com.airguitar.event.MessageSentEvent;
import com.airguitar.repository.MessageRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final ApplicationEventPublisher eventPublisher;

    public MessageService(MessageRepository messageRepository, ApplicationEventPublisher eventPublisher) {
        this.messageRepository = messageRepository;
        this.eventPublisher = eventPublisher;
    }

    public MessageDtos.MessageResponse sendMessage(MessageDtos.SendMessagePayload payload) {
        Message message = new Message();
        message.setInstrumentId(payload.instrumentId());
        message.setSenderId(payload.senderId());
        message.setReceiverId(payload.receiverId());
        message.setConversationId(conversationId(payload.senderId(), payload.receiverId()));
        message.setContent(payload.content());
        message.setTimestamp(Instant.now());
        Message saved = messageRepository.save(message);
        eventPublisher.publishEvent(new MessageSentEvent(saved));
        return toResponse(saved);
    }

    public List<MessageDtos.MessageResponse> getHistory(Long instrumentId, Long me, Long otherUser) {
        return messageRepository.findByInstrumentIdAndConversationIdOrderByTimestampAsc(
                instrumentId, conversationId(me, otherUser)
        ).stream().map(this::toResponse).toList();
    }

    public String conversationId(Long a, Long b) {
        long min = Math.min(a, b);
        long max = Math.max(a, b);
        return min + "_" + max;
    }

    private MessageDtos.MessageResponse toResponse(Message m) {
        return new MessageDtos.MessageResponse(
                m.getId(),
                m.getConversationId(),
                m.getSenderId(),
                m.getReceiverId(),
                m.getInstrumentId(),
                m.getContent(),
                m.getTimestamp()
        );
    }
}
