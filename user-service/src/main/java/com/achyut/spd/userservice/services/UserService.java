package com.achyut.spd.userservice.services;

import com.achyut.spd.userservice.dtos.request.CreateUserRequest;
import com.achyut.spd.userservice.entities.User;

public interface UserService {

	public User createUser(CreateUserRequest userRequest);

}
