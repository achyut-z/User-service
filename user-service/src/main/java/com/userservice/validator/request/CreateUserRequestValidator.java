package com.userservice.validator.request;

import java.util.Objects;

import com.userservice.dto.UserDetailsDto;
import com.userservice.dto.request.CreateUserRequest;
import com.userservice.exception.ResourceNotFoundException;
import com.userservice.validator.EmailValidator;
import com.userservice.validator.UserDetailsValidator;
import com.userservice.validator.UsernameValidator;

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
