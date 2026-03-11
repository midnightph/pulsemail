package com.midnight.pulsemail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.midnight.pulsemail.model.Campaign;

public interface  CampaignRepository extends JpaRepository<Campaign, Long> {
    
}
