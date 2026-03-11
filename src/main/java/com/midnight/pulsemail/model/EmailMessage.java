package com.midnight.pulsemail.model;

import java.io.Serializable;

import lombok.Data;

@Data

public class EmailMessage implements Serializable {

    private Long id;
    private String to;
    private String subject;
    private String body;
    private Long campaignId;
}
