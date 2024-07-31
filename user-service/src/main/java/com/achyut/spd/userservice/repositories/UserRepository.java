package com.achyut.spd.userservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.achyut.spd.userservice.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
