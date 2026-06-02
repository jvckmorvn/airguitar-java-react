package com.airguitar.repository;

import com.airguitar.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByInstrumentIdAndConversationIdOrderByTimestampAsc(Long instrumentId, String conversationId);
}
