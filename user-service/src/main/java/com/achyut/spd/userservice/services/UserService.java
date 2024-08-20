package com.achyut.spd.userservice.services;

import com.achyut.spd.userservice.dtos.request.CreateUserRequest;
import com.achyut.spd.userservice.dtos.response.CredentialResponse;

public interface UserService {

	public CreateUserRequest createUser(CreateUserRequest userRequest);

	public CredentialResponse getUserByUsername(String username);

}
