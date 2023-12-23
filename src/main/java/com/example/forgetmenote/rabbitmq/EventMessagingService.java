package com.example.forgetmenote.rabbitmq;

import com.example.forgetmenote.models.Event;

public interface EventMessagingService {
    void sendEventName(String eventName);
}
