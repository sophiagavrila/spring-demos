package com.revature.controllers;

import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserRepository userRepo;

	@Autowired
	public UserController(UserRepository repo) {
		this.userRepo = repo;
	}

	@GetMapping
	public List<User> getAllUsers() {
		return (List<User>) userRepo.findAll();
	}

	@PostMapping
	public User registerNewUser(@Valid @RequestBody User newUser) {
		newUser.setRole(UserRole.BASIC_USER);
		newUser.setRegisterDateTime(LocalDateTime.now());
		return userRepo.save(newUser);
	}

	/**
	 * It is used to set an entity’s information completely. PUT is similar to POST
	 * in that it can create resources, but it does so when there is a defined URI.
	 * PUT overwrites the entire entity if it already exists, and creates a new
	 * resource if it doesn’t exist.
	 * 
	 * https://www.sourcecodeexamples.net/2019/10/putmapping-spring-boot-example.html
	 */
	@PutMapping
	public User updateUser(@Valid @RequestBody User updatedUser) {
		return userRepo.save(updatedUser);
	}

}
