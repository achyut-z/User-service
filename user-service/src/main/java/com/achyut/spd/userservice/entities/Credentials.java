package com.achyut.spd.userservice.entities;

import org.springframework.data.mongodb.core.index.Indexed;

import lombok.Data;

@Data
public class Credentials {
	
	@Indexed
	private String username;
	
	private String password;
	
	@Indexed
	private String email;

}
