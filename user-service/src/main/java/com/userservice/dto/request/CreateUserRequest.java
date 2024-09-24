package com.userservice.dto.request;

import com.userservice.dto.CredentialDto;
import com.userservice.dto.UserDetailsDto;

import lombok.Data;

@Data
public class CreateUserRequest {
	
	CredentialDto userCredentials;
	
	UserDetailsDto userDetails;

}
