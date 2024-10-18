package com.userservice.validator;

import com.userservice.constant.ExceptionConstants;
import com.userservice.constant.GlobalConstants;

import io.micrometer.common.util.StringUtils;

public class UsernameValidator {

	public static void checkUsername(String username) {

		if (StringUtils.isBlank(username)) {
			throw new IllegalArgumentException(ExceptionConstants.BLANK_USERNAME_ERROR_MSG);
		}

		if (username.length() < 6) {
			throw new IllegalArgumentException(GlobalConstants.USERNAME_LENGTH_NOT_LONG_ERROR_MSG);
		}

		if (username.length() > 20) {
			throw new IllegalArgumentException(GlobalConstants.USERNAME_TOO_LONG_ERROR_MSG);
		}

		username = username.toLowerCase();

		if (Character.isDigit(username.charAt(0))) {
			throw new IllegalArgumentException(GlobalConstants.INITIAL_CHARACTER_NUMERIC);
		}

		username = username.trim();

		username = username.replace(" ", "_");
	}

}
