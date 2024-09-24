package com.achyut.spd.userservice.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.achyut.spd.generator.PasswordGenerator;
import com.achyut.spd.mapper.GenericDynamicMapper;
import com.achyut.spd.userservice.constants.ExceptionConstants;
import com.achyut.spd.userservice.constants.GlobalConstants;
import com.achyut.spd.userservice.dtos.CredentialDto;
import com.achyut.spd.userservice.dtos.request.ChangePassword;
import com.achyut.spd.userservice.dtos.request.CreateUserRequest;
import com.achyut.spd.userservice.dtos.request.SetPassword;
import com.achyut.spd.userservice.dtos.response.CredentialResponse;
import com.achyut.spd.userservice.dtos.response.UserResponse;
import com.achyut.spd.userservice.entities.Credentials;
import com.achyut.spd.userservice.entities.User;
import com.achyut.spd.userservice.exception.ResourceNotFoundException;
import com.achyut.spd.userservice.repositories.UserRepository;
import com.achyut.spd.userservice.services.UserService;
import com.achyut.spd.validator.PasswordValidator;
import com.achyut.spd.validator.request.CreateUserRequestValidator;

import io.micrometer.common.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final GenericDynamicMapper<User, CreateUserRequest> mapper;

    private final GenericDynamicMapper<Credentials, CredentialDto> credentialMapper;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository,
            GenericDynamicMapper<User, CreateUserRequest> mapper,
            GenericDynamicMapper<Credentials, CredentialDto> credentialMapper) {

        this.userRepository = userRepository;
        this.mapper = mapper;
        this.credentialMapper = credentialMapper;

    }

    @Override
    public UserResponse createUser(CreateUserRequest request) {

        CreateUserRequestValidator.validateRequest(request);

        if(this.userRepository.isEmailTaken(request.getUserCredentials()
                .getEmail())) {
            throw new IllegalArgumentException(GlobalConstants.EMAIL_TAKEN);
        }

        if(this.userRepository.isUsernameTaken(request.getUserCredentials()
                .getUsername())) {
            throw new IllegalArgumentException(GlobalConstants.USERNAME_TAKEN);
        }

        User user = new User();

        CredentialDto credentialDto = request.getUserCredentials();
        credentialDto.setPassword(PasswordGenerator.generatePassword(12));

        user = this.mapper.toEntity(request);

        this.userRepository.save(user);

        UserResponse response = new UserResponse();

        response.setCredentials(credentialDto);
        response.setName(user.getUserDetails()
                .getName());
        response.setLastName(user.getUserDetails()
                .getLastName());

        return response;
    }

    @Override
    public CredentialResponse getUserByUsername(String username) {

        if(StringUtils.isBlank(username)) {
            throw new IllegalArgumentException(ExceptionConstants.USERNAME_BLANK);
        }

        User user = this.userRepository.getUserByUsername(username);

        if(user == null) {
            LOGGER.warn("User with username {} not found", username);
            throw new ResourceNotFoundException("USER");
        }

        CredentialDto credentials = this.credentialMapper.toDto(user.getUserCredentials());

        return new CredentialResponse(credentials);
    }

    @Override
    public UserResponse setPasswordForRegisteredUser(SetPassword request) {

        if(StringUtils.isBlank(request.getUsername())) {
            throw new IllegalArgumentException(ExceptionConstants.USERNAME_BLANK);
        }

        if(StringUtils.isBlank(request.getPassword())) {
            throw new IllegalArgumentException(ExceptionConstants.PASSWORD_BLANK);
        }

        PasswordValidator.checkPassword(request.getPassword());

        UserResponse response = new UserResponse();

        User user = this.userRepository.getUserByUsername(request.getUsername());

        if(user == null) {
            LOGGER.warn("User with username {} not found", request.getUsername());
            throw new ResourceNotFoundException("USER");
        }

        user.getUserCredentials()
                .setPassword(request.getPassword());

        CredentialDto credentials = this.credentialMapper.toDto(user.getUserCredentials());

        this.userRepository.save(user);

        response.setName(user.getUserDetails()
                .getName());
        response.setLastName(user.getUserDetails()
                .getLastName());
        response.setCredentials(credentials);

        return response;
    }

    @Override
    public CredentialResponse changePassword(ChangePassword request) {

        User user = this.userRepository.getUserByUsername(request.getUsername());

        if(StringUtils.isBlank(request.getUsername())) {
            throw new IllegalArgumentException(ExceptionConstants.USERNAME_BLANK);
        }

        if(StringUtils.isBlank(request.getPassword())) {
            throw new IllegalArgumentException(ExceptionConstants.PASSWORD_BLANK);
        }

        PasswordValidator.checkPassword(request.getPassword());

        CredentialDto credentials = this.credentialMapper.toDto(user.getUserCredentials());

        if(user.getUserCredentials()
                .getPassword()
                .equals(request.getPassword())) {
            throw new IllegalArgumentException(GlobalConstants.SAME_PASSWORD);
        }

        if(request.getConfirmPassword()
                .equals(request.getPassword())) {
            user.getUserCredentials()
                    .setPassword(request.getPassword());
            credentials.setPassword(request.getPassword());
        } else {
            throw new IllegalArgumentException(ExceptionConstants.PASSWORD_MISMATCH);
        }

        return new CredentialResponse(credentials);
    }

}
