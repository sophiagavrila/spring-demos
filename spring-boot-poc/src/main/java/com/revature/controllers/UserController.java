package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.User;
import com.revature.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	/**
	 * Controller layer shouldn't ever call repository directly. You should always
	 * use the service layer because the service layer encapsulates your business
	 * logic surrounding that call
	 */
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	/**
	 * ResponseEntity is meant to represent the entire HTTP response. You can
	 * control anything that goes into it: status code, headers, and body.
	 * 
	 * https://stackoverflow.com/questions/26549379/when-use-responseentityt-and-restcontroller-for-spring-restful-applications#:~:text=266,headers%2C%20and%20body.
	 */
	@GetMapping("/{id}") // allows the client to send the request http://localhost:5000/api/users/2
	public ResponseEntity<User> findUserById(@PathVariable("id") int id) {

		Optional<User> user = userService.getById(id);

		if (!user.isPresent()) {
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		} else {
			// Builds an HTTP reponse that sends back an OK status with
			// the User object in the body of the response
			return ResponseEntity.ok(user.get());
		}
	}

	@PostMapping
	public User registerNewUser(@Valid @RequestBody User newUser) {
		return userService.processRegister(newUser);
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
		return userService.updateUser(updatedUser);
	}

}
