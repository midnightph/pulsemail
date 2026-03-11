package com.midnight.pulsemail.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Campaign {
    
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String subject;
    private String body;
    @ElementCollection
    private List<String> recipients;
    private String status;
    private LocalDateTime createdAt;

}
