package com.userservice.dto.request;

import lombok.Data;

@Data
public class ChangePassword {

	private String username;

	private String password;

	private String confirmPassword;

}
