package com.userservice.validator.request;

import java.util.Objects;

import com.userservice.dto.UserDetailsDto;
import com.userservice.dto.request.CreateUserRequest;
import com.userservice.exception.ResourceNotFoundException;
import com.userservice.validator.EmailValidator;
import com.userservice.validator.UserDetailsValidator;
import com.userservice.validator.UsernameValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateUserRequestValidator {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreateUserRequestValidator.class);

	public static void validateRequest(CreateUserRequest request) {

		if (Objects.isNull(request)) {
			throw new ResourceNotFoundException(CreateUserRequest.class.getSimpleName());
		}

		String email = request.getUserCredentials().getEmail();

		String username = request.getUserCredentials().getUsername();

		UserDetailsDto details = request.getUserDetails();

		LOGGER.info("Validating email");
		EmailValidator.checkEmail(email);

		LOGGER.info("Validating username");
		UsernameValidator.checkUsername(username);

		LOGGER.info("Validating user details");
		UserDetailsValidator.checkDetails(details);
	}

}
