package com.midnight.pulsemail.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.midnight.pulsemail.model.EmailMessage;
import com.midnight.pulsemail.repository.CampaignRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailConsumer {

    private final JavaMailSender javaMailSender;
    private final CampaignRepository campaignRepository;

    @RabbitListener(queues = "email.queue")
    public void consume(EmailMessage message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        try {
            mailMessage.setTo(message.getTo());
            mailMessage.setSubject(message.getSubject());
            mailMessage.setText(message.getBody());
            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            campaignRepository.findById(message.getCampaignId()).ifPresent(campaign -> {
                campaign.setStatus("FAILED");
                campaignRepository.save(campaign);
            });
            e.printStackTrace();
        }
    }
}
