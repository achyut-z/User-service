package com.achyut.spd.domains;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "User")
public class User {
	
	private String id;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private String phoneNumber;
	
	private UserDetails userDetails;

}
