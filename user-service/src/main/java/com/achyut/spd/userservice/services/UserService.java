package com.achyut.spd.userservice.services;

import com.achyut.spd.userservice.dtos.request.ChangePassword;
import com.achyut.spd.userservice.dtos.request.CreateUserRequest;
import com.achyut.spd.userservice.dtos.request.SetPassword;
import com.achyut.spd.userservice.dtos.response.CredentialResponse;
import com.achyut.spd.userservice.dtos.response.UserResponse;

public interface UserService {

	public UserResponse createUser(CreateUserRequest userRequest);

	public CredentialResponse getUserByUsername(String username);

    public UserResponse setPasswordForRegisteredUser(SetPassword request);

    public CredentialResponse changePassword(ChangePassword request);

}
