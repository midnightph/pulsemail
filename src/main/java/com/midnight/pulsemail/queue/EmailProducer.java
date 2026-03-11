package com.midnight.pulsemail.queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.midnight.pulsemail.model.EmailMessage;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendEmail(EmailMessage message) {
        rabbitTemplate.convertAndSend("email.exchange", "email.routing.key", message);
    }
}
