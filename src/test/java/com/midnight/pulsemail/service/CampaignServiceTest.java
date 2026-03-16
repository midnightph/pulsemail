package com.midnight.pulsemail.service;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.midnight.pulsemail.model.Campaign;
import com.midnight.pulsemail.model.EmailMessage;
import com.midnight.pulsemail.queue.EmailProducer;
import com.midnight.pulsemail.repository.CampaignRepository;

@ExtendWith(MockitoExtension.class)
class CampaignServiceTest {

    @Mock
    private CampaignRepository campaignRepository;

    @Mock
    private EmailProducer emailProducer;

    @InjectMocks
    private CampaignService campaignService;

    @Test
    void createCampaign() {
        Campaign campaign = new Campaign();

        campaign.setSubject("Test");
        campaign.setBody("Body");
        campaign.setRecipients(List.of("test@email.com"));

        when(campaignRepository.save(any())).thenReturn(campaign);
        campaignService.createCampaign(campaign);
        verify(campaignRepository, times(2)).save(any());
        verify(emailProducer).sendEmail(any(EmailMessage.class));
        assertThat(campaign.getStatus()).isEqualTo("SENT");
    }
}