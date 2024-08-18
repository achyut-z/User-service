package com.achyut.spd.userservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.achyut.spd.userservice.dtos.request.CreateUserRequest;
import com.achyut.spd.userservice.entities.User;
import com.achyut.spd.userservice.services.UserService;

@RestController
@RequestMapping("/api/user/")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
		
	}
	
	@PostMapping("create")
	public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
		
		User user = this.userService.createUser(request);
		
		ResponseEntity<?> response = new ResponseEntity<Object>(user, HttpStatus.OK);
		return response;
	}

}
