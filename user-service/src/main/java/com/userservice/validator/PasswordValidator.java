package com.userservice.validator;

import org.springframework.stereotype.Component;

import com.userservice.constant.ExceptionConstants;
import com.userservice.constant.GlobalConstants;

import io.micrometer.common.util.StringUtils;

@Component
public class PasswordValidator {

	public static void checkPassword(String password) {

		// check if password string is blank
		if (StringUtils.isBlank(password)) {
			throw new IllegalArgumentException(ExceptionConstants.BLANK_PASSWORD_ERROR_MSG);
		}

		if (password.length() < 8) {
			throw new IllegalArgumentException(GlobalConstants.PASSWORD_LENGTH_NOT_LONG_ERROR_MSG);
		}

		boolean hasLower = false;
		boolean hasUpper = false;
		boolean hasDigit = false;

		for (int i = 0; i < password.length(); i++) {

			if (Character.isSpaceChar(password.charAt(i))) {
				throw new IllegalArgumentException(ExceptionConstants.SPACE_NOT_ALLOWED);
			}

			if (Character.isLowerCase(password.charAt(i))) {
				hasLower = true;
			}

			if (Character.isUpperCase(password.charAt(i))) {
				hasUpper = true;
			}

			if (Character.isDigit(password.charAt(i))) {
				hasDigit = true;
			}

		}

		if (!hasLower) {
			throw new IllegalArgumentException(ExceptionConstants.NEED_AT_LEAST_ONE_LOWERCASE_ERROR_MSG);
		}

		if (!hasUpper) {
			throw new IllegalArgumentException(ExceptionConstants.NEED_AT_LEAST_ONE_UPPERCASE_ERROR_MSG);
		}

		if (!hasDigit) {
			throw new IllegalArgumentException(ExceptionConstants.NEED_AT_LEAST_ONE_DIGIT_ERROR_MSG);
		}
	}

}
