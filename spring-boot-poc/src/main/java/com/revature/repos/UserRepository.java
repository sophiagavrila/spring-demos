package com.revature.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

//    @Query("from User u where u.username = :username and u.password = :password")
	// property expression
    User findUserByUsernameAndPassword(String username, String password);

}
