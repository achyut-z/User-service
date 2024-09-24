package com.userservice.validator;

import com.userservice.constant.ExceptionConstants;
import com.userservice.constant.GlobalConstants;

import io.micrometer.common.util.StringUtils;

public class UsernameValidator {

    public static void checkUsername(String username) {

        if(StringUtils.isBlank(username)) {
            throw new IllegalArgumentException(ExceptionConstants.USERNAME_BLANK);
        }

        if(username.length() < 6) {
            throw new IllegalArgumentException(GlobalConstants.USERNAME_NOT_LONG);
        }
        
        if(username.length() > 20) {
            throw new IllegalArgumentException(GlobalConstants.USERNAME_TOO_LONG);
        }

        username = username.toLowerCase();
        
        if(Character.isDigit(username.charAt(0))) {
            throw new IllegalArgumentException(GlobalConstants.INITIAL_CHARACTER_NUMERIC);
        }
        
        username = username.trim();
        
        username = username.replace(" ", "_");
    }

}
