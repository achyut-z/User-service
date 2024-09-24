package com.achyut.spd.userservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.achyut.spd.userservice.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	@Query("{'userCredentials.username': ?0}")
	public User getUserByUsername(String username);
	
	@Query(value = "{'userCredentials.username': ?0}", exists = true)
	boolean isUsernameTaken(String username);
	
	@Query(value = "{'userCredentials.email': ?0}", exists = true)
	boolean isEmailTaken(String email);

}
