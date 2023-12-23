package com.example.forgetmenote.rabbitmq;

import com.example.forgetmenote.models.Event;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitEventMessagingService implements EventMessagingService{
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitEventMessagingService(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendEventName(String eventName){
        MessageConverter converter = rabbitTemplate.getMessageConverter();
        MessageProperties properties = new MessageProperties();
        Message message = converter.toMessage(eventName,properties);
        rabbitTemplate.convertAndSend("forgetmenote_routing_key",message);
    }

}
