package com.userservice.validator;

import com.userservice.constant.ExceptionConstants;
import com.userservice.constant.GlobalConstants;
import com.userservice.dto.AddressDto;
import com.userservice.dto.UserDetailsDto;

import java.util.List;
import java.util.Objects;

public class UserDetailsValidator {

	public static void checkDetails(UserDetailsDto details) {

		String name = details.getName();

		String lastName = details.getLastName();

		String dateOfBirth = details.getDateOfBirth();

		List<String> phoneNumbers = details.getPhoneNumbers();

		if (phoneNumbers != null && !phoneNumbers.isEmpty()) {
			phoneNumbers.forEach(PhoneNumberValidator::checkNumber);
		}

		AddressDto address = details.getAddress();

		String city = address.getCity();

		String state = address.getState();

		Integer zipCode = address.getZipCode();

		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("Name cannot be blank");
		}

		if (lastName == null || lastName.isBlank()) {
			throw new IllegalArgumentException("Last name cannot be blank");
		}

		if (dateOfBirth == null || dateOfBirth.isBlank()) {
			throw new IllegalArgumentException("Date of birth cannot be blank");
		}

		if (city == null || city.isBlank()) {
			throw new IllegalArgumentException("City name cannot be null");
		}

		if (state == null || state.isBlank()) {
			throw new IllegalArgumentException("State name cannot be null");
		}

		if (Objects.isNull(zipCode)) {
			throw new IllegalArgumentException("Zip code cannot be null");
		}

		DateValidator.checkDate(dateOfBirth);
		name = validateString(name);
		lastName = validateString(lastName);
		city = validateString(city);
		state = validateString(state);
		validateInteger(zipCode);

		details.setName(name);
		details.setLastName(lastName);
		address.setCity(city);
		address.setState(state);
	}

	private static String validateString(String value) {

		if (value.length() < 3) {
			throw new IllegalArgumentException(GlobalConstants.STRING_VALUE_TOO_SHORT_ERROR_MSG);
		}

		for (int i = 0; i < value.length(); i++) {
			if (Character.isDigit(value.charAt(i))) {
				throw new IllegalArgumentException(ExceptionConstants.DIGIT_NOT_ALLOWED);
			}

			if (!Character.isAlphabetic(value.charAt(i))) {
				throw new IllegalArgumentException(ExceptionConstants.ONLY_ALPHABETIC_CHARACTERS_ALLOWED);
			}
		}
		// capitalize first letter
		String name = String.valueOf(Character.toUpperCase(value.charAt(0)));
		// convert the string to lowercase
		value = value.substring(1).toLowerCase();
		return name + value;
	}

	private static void validateInteger(Integer value) {

		if (value < 0) {
			throw new IllegalArgumentException(GlobalConstants.INVALID_ZIP_CODE_FORMAT);
		}

		if (value < 100000 || value > 999999) {
			throw new IllegalArgumentException(GlobalConstants.INVALID_ZIP_CODE_FORMAT);
		}
	}

}
