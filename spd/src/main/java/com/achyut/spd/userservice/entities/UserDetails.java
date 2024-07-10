package com.achyut.spd.userservice.entities;

import lombok.Data;

@Data
public class UserDetails {
	
	private String name;
	
	private String lastName;
	
	private Address address;
	
	private String dateOfBirth;

}
