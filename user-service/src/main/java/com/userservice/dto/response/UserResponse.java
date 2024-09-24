package com.achyut.spd.userservice.dtos.response;

import com.achyut.spd.userservice.dtos.CredentialDto;

import lombok.Data;

@Data
public class UserResponse {
	
	private String name;
	
	private String lastName;
	
	private CredentialDto credentials;

}
