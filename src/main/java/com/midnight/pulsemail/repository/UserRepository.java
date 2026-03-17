package com.midnight.pulsemail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.midnight.pulsemail.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    public User findByEmail(String email);

}
