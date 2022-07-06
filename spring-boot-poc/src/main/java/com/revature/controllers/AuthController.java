package com.revature.controllers;

import com.revature.dtos.Credentials;
import com.revature.models.User;
import com.revature.repos.UserRepository;
import com.revature.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @RestController is a convenience annotation for creating Restful controllers.
 *                 It is a specialization of @Component and is autodetected
 *                 through classpath scanning. It adds the @Controller
 *                 and @ResponseBody annotations. It converts the response to
 *                 JSON or XML.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

	private UserService userService;

	@Autowired
	public AuthController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<User> login(@Valid @RequestBody Credentials creds) {
		User user = userService.processLogin(creds.getUsername(), creds.getPassword());

		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		} else {
			return ResponseEntity.ok(user);
		}

	}
}
