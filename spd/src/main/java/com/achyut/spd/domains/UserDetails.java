package com.achyut.spd.domains;

import lombok.Data;

@Data
public class UserDetails {
	
	private String name;
	
	private String lastName;
	
	private Address address;
	
	private String dateOfBirth;

}
