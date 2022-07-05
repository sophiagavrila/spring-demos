package com.revature.repos;

import com.revature.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

//    @Query("from User u where u.username = :username and u.password = :password")
    User findUserByUsernameAndPassword(String username, String password);

}
