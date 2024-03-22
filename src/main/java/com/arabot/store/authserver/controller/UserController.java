package com.arabot.store.authserver.controller;


import com.arabot.store.authserver.dto.LoginRequest;
import com.arabot.store.authserver.dto.UserRequest;
import com.arabot.store.authserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public  ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {

        return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> createUser(@RequestBody LoginRequest loginRequest) {

        return new ResponseEntity<>(userService.login(loginRequest), HttpStatus.OK);
    }

}
