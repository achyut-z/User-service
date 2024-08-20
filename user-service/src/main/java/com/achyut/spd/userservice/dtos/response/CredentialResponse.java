package com.achyut.spd.userservice.dtos.response;

import com.achyut.spd.userservice.dtos.CredentialDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CredentialResponse {
	
	private CredentialDto credentials;
	
	private String message;
	
	public CredentialResponse(CredentialDto credentials) {
		this.credentials = credentials;
	}
	
	public CredentialResponse(String message) {
		this.message = message;
	}

}
