package com.midnight.pulsemail.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String password;
    
}
