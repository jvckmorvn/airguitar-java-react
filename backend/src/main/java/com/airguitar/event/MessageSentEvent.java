package com.airguitar.event;

import com.airguitar.entity.Message;

public record MessageSentEvent(Message message) {}
