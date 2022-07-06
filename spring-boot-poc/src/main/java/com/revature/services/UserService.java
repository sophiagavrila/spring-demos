package com.revature.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.repos.UserRepository;

@Service	
public class UserService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private UserRepository userRepo;

	/**
	 * Best practice is to always include @Autowired over the Constructor
	 * https://stackoverflow.com/questions/40620000/spring-autowire-on-properties-vs-constructor#:~:text=357,calling%20the%20constructor
	 * @param userRepo
	 */
	@Autowired
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	/**
	 * @Transactional on Service methods:
	 * 				  In the case that multiple dao methods were
	 *                being called, we want to make sure that those methods are
	 *                fired against the DB in one unit of work (transaction).
	 */
	// Every time that this method is invoked, we want to begin a new Transaction (unit of work against the DB)
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public User processRegister(User newUser) {
		newUser.setRole(UserRole.BASIC_USER);
		newUser.setRegisterDateTime(LocalDateTime.now());
		return userRepo.save(newUser);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public User processLogin(String username, String password) {
		return userRepo.findUserByUsernameAndPassword(username, password);
	}
	
	@Transactional(readOnly=true)
	public Optional<User> getById(int id) {
		
		if (id <= 0) {
			log.warn("Id cannot be <= 0. Id passed was: {}", id);
			return null;
		} else {
			return userRepo.findById(id);
		}
		
	}
	
	@Transactional(readOnly=true)
	public List<User> getAllUsers() {
		return (List<User>) userRepo.findAll();
	}
	
	@Transactional(propagation=Propagation.REQUIRED) // default setting of transactions in Spring
	public User updateUser(User updatedUser) {
		return userRepo.save(updatedUser);
	}
	
}
