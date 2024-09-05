package com.achyut.spd.validator.request;

import java.util.List;

import com.achyut.spd.userservice.dtos.request.CreateUserRequest;
import com.achyut.spd.validator.EmailValidator;
import com.achyut.spd.validator.PhoneNumberValidator;
import com.achyut.spd.validator.UsernameValidator;

public class CreateUserRequestValidator {

    public static void validateRequest(CreateUserRequest request) {

        String email = request.getUserCredentials()
                .getEmail();

        String username = request.getUserCredentials()
                .getUsername();

        List<String> phoneNumbers = request.getUserDetails()
                .getPhoneNumbers();

        EmailValidator.checkEmail(email);

        UsernameValidator.checkUsername(username);

        if(!(phoneNumbers.isEmpty())) {
            phoneNumbers.forEach(ph -> PhoneNumberValidator.checkNumber(ph));
        }
    }

}
