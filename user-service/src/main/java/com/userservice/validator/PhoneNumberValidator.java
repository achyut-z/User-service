package com.achyut.spd.validator;

import org.springframework.stereotype.Component;

import com.achyut.spd.userservice.constants.ExceptionConstants;
import com.achyut.spd.userservice.constants.GlobalConstants;

import io.micrometer.common.util.StringUtils;

@Component
public class PhoneNumberValidator {

    public static void checkNumber(String number) {

        if(StringUtils.isBlank(number)) {
            throw new IllegalArgumentException(ExceptionConstants.PHONE_BLANK);
        }

        if(number.length() > 12 || number.length() < 10) {
            throw new IllegalArgumentException(GlobalConstants.INVALID_PHONE_FORMAT);
        }

        if(number.length() == 12) {

            if(!(number.startsWith("+91"))) {
                throw new IllegalArgumentException(GlobalConstants.INVALID_PHONE_FORMAT);
            }

            if(!(number.substring(3)
                    .matches("\\d{9}"))) {
                throw new IllegalArgumentException(GlobalConstants.INVALID_PHONE_FORMAT);
            }

        } else if(number.length() == 10) {

            if(!(number.startsWith("7") || number.startsWith("9"))) {
                throw new IllegalArgumentException(GlobalConstants.INVALID_PHONE_FORMAT);
            }

            if(!number.matches("\\d{10}")) {
                throw new IllegalArgumentException(GlobalConstants.INVALID_PHONE_FORMAT);
            }
        }
    }

}
