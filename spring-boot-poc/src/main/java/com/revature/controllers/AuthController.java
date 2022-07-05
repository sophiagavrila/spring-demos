package com.revature.controllers;

import com.revature.dtos.Credentials;
import com.revature.models.User;
import com.revature.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserRepository userRepo;

    @Autowired
    public AuthController(UserRepository repo) {
        this.userRepo= repo;
    }

    @PostMapping
    public User login(@Valid @RequestBody Credentials creds) {
        return userRepo.findUserByUsernameAndPassword(creds.getUsername(), creds.getPassword());
    }
}
