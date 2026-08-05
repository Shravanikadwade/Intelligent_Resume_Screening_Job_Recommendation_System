package com.aiResumeApplication.ai_resume_system.service;

import com.aiResumeApplication.ai_resume_system.model.LoginRequest;
import com.aiResumeApplication.ai_resume_system.model.LoginResponse;
import com.aiResumeApplication.ai_resume_system.model.User;
import com.aiResumeApplication.ai_resume_system.repository.UserRepository;
import com.aiResumeApplication.ai_resume_system.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public LoginResponse loginUser(LoginRequest loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {

            String token = jwtService.generateToken(user.getEmail());

            return new LoginResponse(
                    "Login Successful",
                    token
            );
        }

        return new LoginResponse(
                "Invalid Password",
                null
        );
    }
}

