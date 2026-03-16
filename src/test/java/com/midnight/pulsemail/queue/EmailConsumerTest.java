package com.midnight.pulsemail.queue;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import static org.mockito.ArgumentMatchers.any;
import com.midnight.pulsemail.model.EmailMessage;
import com.midnight.pulsemail.repository.CampaignRepository;

@ExtendWith(MockitoExtension.class)
public class EmailConsumerTest {

    @Mock
    private JavaMailSender javaMailSender;
    @Mock
    private CampaignRepository campaignRepository;
    @InjectMocks
    private EmailConsumer emailConsumer;

    @Test
    void consume() {
        EmailMessage message = new EmailMessage();

        message.setTo("test@email.com");
        message.setSubject("Test subject");
        message.setBody("Test body");
        message.setCampaignId(1L);

        emailConsumer.consume(message);
        verify(javaMailSender).send(any(SimpleMailMessage.class));

    }

}
