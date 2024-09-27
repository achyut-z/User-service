package com.userservice.constant;

import java.util.Arrays;
import java.util.List;

/**
 * Declare validation related messages and other generic purposed strings here
 *
 * @author Achyut
 */
public class GlobalConstants {

	public static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static final String LOWERCASE = UPPERCASE.toLowerCase();

	public static final String DIGITS = "0123456789";

	public static final String SPECIALS = "`~!@#$%^&*()-_=+[]{}\\|:;\"',./<>?";

	public static final List<String> VALID_DATE_FORMATS = Arrays.asList("dd/MM/yy", "dd-MM-yy", "dd.MM.yy", // 01
																											// 01
																											// 24
			"MM/dd/yy", "MM-dd-yy", "MM.dd.yy", // 12 25 24
			"yy/MM/dd", "yy-MM-dd", "yy.MM.dd", // 24 10 31
			"dd/MMM/yy", "dd-MMM-yy", "dd.MMM.yy", // 01 Jan 24
			"MMM/dd/yy", "MMM-dd-yy", "MMM.dd.yy", // Dec 25 24
			"yy/MMM/dd", "yy-MMM-dd", "yy.MMM.dd", // 24 Oct 31
			"dd/MMMM/yy", "dd-MMMM-yy", "dd.MMMM.yy", // 01 January 24
			"MMMM/dd/yy", "MMMM-dd-yy", "MMMM.dd.yy", // December 25 24
			"yy/MMMM/dd", "yy-MMMM-dd", "yy.MMMM.dd", // 24 October 31
			"dd/MM/yyyy", "dd-MM-yyyy", "dd.MM.yyyy", // 01 01 2024
			"MM/dd/yyyy", "MM-dd-yyyy", "MM.dd.yyyy", // 12 25 2024
			"yyyy/MM/dd", "yyyy-MM-dd", "yyyy.MM.dd", // 2024 10 31
			"dd/MMM/yyyy", "dd-MMM-yyyy", "dd.MMM.yyyy", // 01 Jan 2024
			"MMM/dd/yyyy", "MMM-dd-yyyy", "MMM.dd.yyyy", // Dec 25 2024
			"yyyy/MMM/dd", "yyyy-MMM-dd", "yyyy.MMM.dd", // 2024 Oct 31
			"dd/MMMM/yyyy", "dd-MMMM-yyyy", "dd.MMMM.yyyy", // 01 January 2024
			"MMMM/dd/yyyy", "MMMM-dd-yyyy", "MMMM.dd.yyyy", // December 25 2024
			"yyyy/MMMM/dd", "yyyy-MMMM-dd", "yyyy.MMMM.dd" // 2024 October 31
	);

	public static final String USERNAME_TAKEN = "Username is taken. Please choose another";

	public static final String INVALID_EMAIL_FORMAT = "Email format invalid. Please enter a valid email format";

	public static final String EMAIL_TAKEN = "Email is already used. Please choose another";

	public static final String SAME_PASSWORD = "New password cannot be same as old password";

	public static final String PASSWORD_NOT_LONG = "Password length needs to be at least 8 characters";

	public static final String USERNAME_NOT_LONG = "Username length needs to be at least 6 characters";

	public static final String INITIAL_CHARACTER_NUMERIC = "Username cannot start from a numerical character";

	public static final String USERNAME_TOO_LONG = "Allowed username length is up to 20 characters only";

	public static final String INVALID_PHONE_FORMAT = "Phone number format invalid. Please enter a valid phone number";

	public static final String VALUE_NOT_LONG = "String value needs to be at least 3 characters";

	public static final String INVALID_ZIP_FORMAT = "Zip code format invalid. Please enter a valid zip code";

}
