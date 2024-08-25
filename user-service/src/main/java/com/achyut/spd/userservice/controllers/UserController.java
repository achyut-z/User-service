package com.achyut.spd.userservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.achyut.spd.userservice.dtos.request.ChangePassword;
import com.achyut.spd.userservice.dtos.request.CreateUserRequest;
import com.achyut.spd.userservice.dtos.request.SetPassword;
import com.achyut.spd.userservice.dtos.response.CredentialResponse;
import com.achyut.spd.userservice.dtos.response.UserResponse;
import com.achyut.spd.userservice.services.UserService;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;

    }

    @PostMapping("create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {

        try {

            UserResponse user = this.userService.createUser(request);

            return new ResponseEntity<>(user, HttpStatus.OK);

        } catch(IllegalArgumentException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable("username") String username) {

        CredentialResponse userCredentials = this.userService.getUserByUsername(username);

        ResponseEntity<?> response = new ResponseEntity<>(userCredentials, HttpStatus.OK);
        return response;
    }

    @PutMapping("/setPassword")
    public ResponseEntity<?> setPassword(@RequestBody SetPassword request) {

        try {

            UserResponse credentials = this.userService.setPasswordForRegisteredUser(request);

            return new ResponseEntity<>(credentials, HttpStatus.OK);

        } catch(IllegalArgumentException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }

    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePassword request) {

        try {

            CredentialResponse credentials = this.userService.changePassword(request);

            return new ResponseEntity<>(credentials, HttpStatus.OK);

        } catch(IllegalArgumentException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

}
