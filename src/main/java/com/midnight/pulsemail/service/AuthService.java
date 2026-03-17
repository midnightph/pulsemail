package com.midnight.pulsemail.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.midnight.pulsemail.model.User;
import com.midnight.pulsemail.repository.UserRepository;
import com.midnight.pulsemail.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(String email, String password) {

        String cryptPassword = passwordEncoder.encode(password);

        User user = new User();
        user.setEmail(email);
        user.setPassword(cryptPassword);

        userRepository.save(user);

        return jwtService.generateToken(email);

    }

}
