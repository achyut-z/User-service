package com.achyut.spd.generator;

import java.util.Random;

public class PasswordGenerator {

	private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private static final String LOWERCASE = UPPERCASE.toLowerCase();

	private static final String DIGITS = "0123456789";

	private static final String SPECIALS = "`~!@#$%^&*()-_=+[]{}\\|:;\"\',./<>?";

	private static final String ALL_CHARS = UPPERCASE + LOWERCASE + DIGITS + SPECIALS;

	public static String generatePassword(int length) {

		StringBuilder password = new StringBuilder(length);

		for (int i = 0; i < length; i++) {

			password.append(ALL_CHARS.charAt(new Random().nextInt(ALL_CHARS.length())));
		}

		return password.toString();
	}
}
