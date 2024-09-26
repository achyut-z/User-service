package com.userservice.validator;

import com.userservice.constant.ExceptionConstants;
import com.userservice.constant.GlobalConstants;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberValidator {

    public static void checkNumber(String number) {

        if (StringUtils.isBlank(number)) {
            throw new IllegalArgumentException(ExceptionConstants.PHONE_BLANK);
        }

        if (number.length() > 12 || number.length() < 10) {
            throw new IllegalArgumentException(GlobalConstants.INVALID_PHONE_FORMAT);
        }

        if (number.length() == 12) {

            if (!(number.startsWith("+91"))) {
                throw new IllegalArgumentException(GlobalConstants.INVALID_PHONE_FORMAT);
            }

            if (!(number.substring(3)
                    .matches("\\d{9}"))) {
                throw new IllegalArgumentException(GlobalConstants.INVALID_PHONE_FORMAT);
            }

        } else if (number.length() == 10) {

            if (!(number.startsWith("7") || number.startsWith("8") || number.startsWith("9"))) {
                throw new IllegalArgumentException(GlobalConstants.INVALID_PHONE_FORMAT);
            }

            if (!number.matches("\\d{10}")) {
                throw new IllegalArgumentException(GlobalConstants.INVALID_PHONE_FORMAT);
            }
        }
    }

}
