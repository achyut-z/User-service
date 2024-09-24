package com.achyut.spd.userservice.entities;

import java.util.List;

import lombok.Data;

@Data
public class UserDetails {
	
	private String name;
	
	private String lastName;
	
	private String dateOfBirth;
	
	private List<String> phoneNumbers;
	
	private Address address;

}
