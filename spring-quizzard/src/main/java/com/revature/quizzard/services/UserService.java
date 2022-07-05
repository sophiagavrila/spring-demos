package com.revature.quizzard.services;

import com.revature.quizzard.exceptions.*;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.models.UserRole;
import com.revature.quizzard.repositories.UserRepository;
import com.revature.quizzard.web.dtos.Credentials;
import com.revature.quizzard.web.dtos.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepo;

    @Autowired
    public UserService(UserRepository repo) {
        System.out.println("UserService#new invoked!");
        userRepo = repo;
    }

    @Transactional(readOnly=true)
    public List<AppUser> getAllUsers() {

        try {
            return userRepo.findAll();
        } catch (Exception e) {
            throw new QuizzardException(e);
        }

    }

    @Transactional(readOnly=true)
    public AppUser findUserById(int id) {

        if (id <= 0) {
            throw new InvalidRequestException("Id cannot be less than or equal to zero!");
        }

        try {
            return userRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
        } catch (Exception e) {
            throw new QuizzardException(e);
        }

    }

    @Transactional
    public AppUser findUserByUsername(String username) {

        if (username == null || username.equals("")) {
            throw new InvalidRequestException("The provided username was null or empty!");
        }

        try {
            return userRepo.findUserByUsername(username).orElseThrow(ResourceNotFoundException::new);
        } catch (Exception e) {
            throw new QuizzardException(e);
        }

    }

    @Transactional
    public AppUser register(AppUser newUser) {

        if(!isAppUserValid(newUser)) {
            throw new InvalidRequestException("There were invalid fields in the provided AppUser object!");
        }

        if (!isUsernameAvailable(newUser.getUsername())) {
            throw new ResourcePersistenceException("The provided username is already taken!");
        }

        if (!isEmailAvailable(newUser.getEmail())) {
            throw new ResourcePersistenceException("The provided email is already taken!");
        }

        newUser.setRole(UserRole.BASIC_USER);

        try {
            userRepo.save(newUser);
        } catch (Exception e) {
            throw new ResourcePersistenceException("Could not persist new AppUser!", e);
        }

        return newUser;

    }

    @Transactional(readOnly=true)
    public boolean isUsernameAvailable(String username) {

        if (username == null || username.equals("")) {
            throw new InvalidRequestException("Username cannot be null or empty!");
        }

        try {
            return !userRepo.findUserByUsername(username).isPresent();
        } catch (Exception e) {
            throw new QuizzardException(e);
        }

    }

    @Transactional(readOnly=true)
    public boolean isEmailAvailable(String email) {

        if (email == null || email.equals("")) {
            throw new InvalidRequestException("Email cannot be null or empty!");
        }

        try {
            return !userRepo.findUserByEmail(email).isPresent();
        } catch (Exception e) {
            throw new QuizzardException(e);
        }

    }

    @Transactional
    public Principal authenticate(Credentials creds) {

        if (creds == null || creds.getUsername() == null || creds.getPassword() == null
            || creds.getUsername().equals("") || creds.getPassword().equals("")) {
            throw new InvalidRequestException("Invalid credentials object provided!");
        }

        try {

            AppUser authUser = userRepo.findUserByUsernameAndPassword(creds.getUsername(), creds.getPassword())
                    .orElseThrow(AuthenticationException::new);

            return new Principal(authUser);

        } catch (Exception e) {
            throw new QuizzardException(e);
        }

    }

    public boolean isAppUserValid(AppUser candidate) {
        if (candidate.getEmail() == null || candidate.getEmail().equals("")) return false;
        if (candidate.getUsername() == null || candidate.getUsername().equals("")) return false;
        if (candidate.getPassword() == null || candidate.getPassword().equals("")) return false;
        return true;
    }

}
