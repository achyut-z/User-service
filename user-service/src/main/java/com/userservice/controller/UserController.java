package com.userservice.controller;

import com.userservice.dto.request.ChangePassword;
import com.userservice.dto.request.CreateUserRequest;
import com.userservice.dto.request.SetPassword;
import com.userservice.dto.response.CredentialResponse;
import com.userservice.dto.response.UserResponse;
import com.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;

    }

    @PostMapping("create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {

        UserResponse user = this.userService.createUser(request);

        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @GetMapping("{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable("username") String username) {

        CredentialResponse userCredentials = this.userService.getUserByUsername(username);

        return new ResponseEntity<>(userCredentials, HttpStatus.OK);
    }

    @PutMapping("/setPassword")
    public ResponseEntity<?> setPassword(@RequestBody SetPassword request) {

        UserResponse credentials = this.userService.setPasswordForRegisteredUser(request);

        return new ResponseEntity<>(credentials, HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePassword request) {

        CredentialResponse credentials = this.userService.changePassword(request);

        return new ResponseEntity<>(credentials, HttpStatus.OK);

    }

}
