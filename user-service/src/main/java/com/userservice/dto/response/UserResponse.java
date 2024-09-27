package com.userservice.dto.response;

import com.userservice.dto.CredentialDto;

import lombok.Data;

@Data
public class UserResponse {

	private String name;

	private String lastName;

	private CredentialDto credentials;

}
