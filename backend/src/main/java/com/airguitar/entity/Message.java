package com.airguitar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String conversationId;

    @Column(nullable = false)
    private Long senderId;

    @Column(nullable = false)
    private Long receiverId;

    @Column(nullable = false)
    private Long instrumentId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private Instant timestamp;

    public Long getId() { return id; }
    public String getConversationId() { return conversationId; }
    public Long getSenderId() { return senderId; }
    public Long getReceiverId() { return receiverId; }
    public Long getInstrumentId() { return instrumentId; }
    public String getContent() { return content; }
    public Instant getTimestamp() { return timestamp; }
    public void setId(Long id) { this.id = id; }
    public void setConversationId(String conversationId) { this.conversationId = conversationId; }
    public void setSenderId(Long senderId) { this.senderId = senderId; }
    public void setReceiverId(Long receiverId) { this.receiverId = receiverId; }
    public void setInstrumentId(Long instrumentId) { this.instrumentId = instrumentId; }
    public void setContent(String content) { this.content = content; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
}
