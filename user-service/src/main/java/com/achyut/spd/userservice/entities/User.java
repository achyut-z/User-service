package com.achyut.spd.userservice.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "User")
public class User {
	
	@Id
	private String id;	
	
	private Credentials userCredentials;
	
	private UserDetails userDetails;

}
