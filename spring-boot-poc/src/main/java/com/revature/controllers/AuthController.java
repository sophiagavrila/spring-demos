package com.revature.controllers;

import com.revature.dtos.Credentials;
import com.revature.models.User;
import com.revature.repos.UserRepository;
import com.revature.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService= userService;
    }

    @PostMapping
    public User login(@Valid @RequestBody Credentials creds) {
        return userService.processLogin(creds.getUsername(), creds.getPassword());
    }
}
