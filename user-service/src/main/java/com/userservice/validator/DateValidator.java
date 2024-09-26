package com.userservice.validator;

import com.userservice.constant.ExceptionConstants;
import com.userservice.constant.GlobalConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateValidator.class);

    public static void checkDate(String date) {

        boolean valid = false;

        for (String format : GlobalConstants.VALID_DATE_FORMATS) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            try {
                LocalDate.parse(date, formatter);
                valid = true;
                break;
            } catch (DateTimeParseException ex) {
                LOGGER.warn("{} date format did not match. Trying another format", format);
            }
        }

        if (!valid) {
            throw new IllegalArgumentException(ExceptionConstants.INVALID_DATE_FORMAT);
        }
    }
}
