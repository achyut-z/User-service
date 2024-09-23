package com.achyut.spd.validator.request;

import java.util.Objects;
import java.util.Optional;

import com.achyut.spd.userservice.dtos.UserDetailsDto;
import com.achyut.spd.userservice.dtos.request.CreateUserRequest;
import com.achyut.spd.userservice.exception.ResourceNotFoundException;
import com.achyut.spd.validator.EmailValidator;
import com.achyut.spd.validator.UserDetailsValidator;
import com.achyut.spd.validator.UsernameValidator;

public class CreateUserRequestValidator {

    public static void validateRequest(CreateUserRequest request) {
        
        if(Objects.isNull(request)) {
            throw new ResourceNotFoundException(CreateUserRequest.class.getSimpleName());
        }
        
        String email = request.getUserCredentials()
                .getEmail();

        String username = request.getUserCredentials()
                .getUsername();
        
        UserDetailsDto details = request.getUserDetails();

        EmailValidator.checkEmail(email);

        UsernameValidator.checkUsername(username);

        UserDetailsValidator.checkDetails(details);
    }

}
