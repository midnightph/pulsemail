package com.midnight.pulsemail.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.midnight.pulsemail.model.Campaign;
import com.midnight.pulsemail.model.EmailMessage;
import com.midnight.pulsemail.queue.EmailProducer;
import com.midnight.pulsemail.repository.CampaignRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final EmailProducer emailProducer;
    private final CampaignRepository campaignRepository;

    public Campaign createCampaign(Campaign campaign) {
        campaign.setStatus("PENDING");
        campaign.setCreatedAt(LocalDateTime.now());

        Campaign saved = campaignRepository.save(campaign);

        saved.getRecipients().forEach(recipient -> {
            EmailMessage emailMessage = new EmailMessage();
            emailMessage.setTo(recipient);
            emailMessage.setSubject(saved.getSubject());
            emailMessage.setBody(saved.getBody());
            emailMessage.setCampaignId(saved.getId());
            emailProducer.sendEmail(emailMessage);
        });

        return saved;
    }

}
