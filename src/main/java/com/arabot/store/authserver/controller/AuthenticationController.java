package com.arabot.store.authserver.controller;

import com.arabot.store.authserver.dto.LoginRequest;
import com.arabot.store.authserver.dto.Token;
import com.arabot.store.authserver.dto.UserRequest;
import com.arabot.store.authserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;





}
