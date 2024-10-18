package com.userservice.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.userservice.constant.ExceptionConstants;
import com.userservice.constant.GlobalConstants;

@Component
public class EmailValidator {

	private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

	// Validate email syntax
	private static boolean isValidEmailFormat(String email) {

		return EMAIL_PATTERN.matcher(email).matches();
	}

	public static void checkEmail(String email) {

		if (email == null || email.isBlank()) {
			throw new IllegalArgumentException(ExceptionConstants.BLANK_EMAIL_ERROR_MSG);
		}

		if (!isValidEmailFormat(email)) {
			throw new IllegalArgumentException(GlobalConstants.INVALID_EMAIL_FORMAT);
		}
	}

}
