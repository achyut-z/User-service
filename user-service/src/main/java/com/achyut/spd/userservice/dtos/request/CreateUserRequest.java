package com.achyut.spd.userservice.dtos.request;

import com.achyut.spd.userservice.dtos.CredentialDto;
import com.achyut.spd.userservice.dtos.UserDetailsDto;

import lombok.Data;

@Data
public class CreateUserRequest {
	
	CredentialDto userCredentials;
	
	UserDetailsDto userDetails;

}
