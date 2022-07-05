package com.revature.quizzard.web.controllers;

import com.revature.quizzard.exceptions.ResourcePersistenceException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.web.dtos.ErrorResponse;
import com.revature.quizzard.web.dtos.Principal;
import com.revature.quizzard.web.security.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController implies
//      - @Controller at the class level
//      - @ResponseBody on the return type of each mapping method
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService service) {
        System.out.println("UserController#new invoked!");
        this.userService = service;
    }

    @Secured(allowedRoles={"Admin", "Dev"})
    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public List<AppUser> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value="/id/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public AppUser getUserById(@PathVariable int id) {
        return userService.findUserById(id);
    }

    @GetMapping(value="/search", produces=MediaType.APPLICATION_JSON_VALUE)
    public AppUser getUserByUsername(@RequestParam String username) {
        return userService.findUserByUsername(username);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public AppUser registerNewUser(@RequestBody AppUser newUser) {
        return userService.register(newUser);
    }

}
