package com.achyut.spd.userservice.dtos;

import java.util.List;

import lombok.Data;

@Data
public class UserDetailsDto {
	
	private String name;
	
	private String lastName;
	
	private String dateOfBirth;
	
	private List<String> phoneNumbers;
	
	private AddressDto address;

}
