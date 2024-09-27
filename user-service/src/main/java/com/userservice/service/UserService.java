package com.userservice.service;

import com.userservice.dto.request.ChangePassword;
import com.userservice.dto.request.CreateUserRequest;
import com.userservice.dto.request.SetPassword;
import com.userservice.dto.response.CredentialResponse;
import com.userservice.dto.response.UserResponse;

public interface UserService {

	public UserResponse createUser(CreateUserRequest userRequest);

	public CredentialResponse getUserByUsername(String username);

	public UserResponse setPasswordForRegisteredUser(SetPassword request);

	public CredentialResponse changePassword(ChangePassword request);

}
