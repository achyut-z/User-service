package com.userservice.constant;

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

}
