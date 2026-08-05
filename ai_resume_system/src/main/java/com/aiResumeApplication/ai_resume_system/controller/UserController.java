package com.aiResumeApplication.ai_resume_system.controller;

import com.aiResumeApplication.ai_resume_system.model.LoginRequest;
import com.aiResumeApplication.ai_resume_system.model.LoginResponse;
import com.aiResumeApplication.ai_resume_system.model.User;
import com.aiResumeApplication.ai_resume_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


    @RestController
    @RequestMapping("/users")
    public class UserController {

        @Autowired
        private UserService userService;

        @PostMapping("/register")
        public User registerUser(@RequestBody User user) {

            return userService.registerUser(user);
        }

        @PostMapping("/login")
        public LoginResponse loginUser(@RequestBody LoginRequest loginRequest) {

            return userService.loginUser(loginRequest);
        }
    }

