package com.achyut.spd.generator;

import java.util.Random;

import com.achyut.spd.userservice.constants.GlobalConstants;

public class PasswordGenerator {

    private static final String ALL_CHARS = GlobalConstants.UPPERCASE + GlobalConstants.LOWERCASE
            + GlobalConstants.DIGITS + GlobalConstants.SPECIALS;

    public static String generatePassword(int length) {

        StringBuilder password = new StringBuilder(length);

        for(int i = 0; i < length; i++) {

            password.append(ALL_CHARS.charAt(new Random().nextInt(ALL_CHARS.length())));
        }

        return password.toString();
    }
}
