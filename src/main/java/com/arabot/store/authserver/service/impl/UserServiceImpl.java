package com.arabot.store.authserver.service.impl;

import com.arabot.store.authserver.dto.LoginRequest;
import com.arabot.store.authserver.dto.Token;
import com.arabot.store.authserver.dto.UserCreatedResponse;
import com.arabot.store.authserver.dto.UserRequest;
import com.arabot.store.authserver.model.Role;
import com.arabot.store.authserver.model.User;
import com.arabot.store.authserver.service.JwtService;
import com.arabot.store.authserver.service.UserService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.arabot.store.authserver.repository.UserRepository;


import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.*;

import static org.springframework.security.oauth2.jose.jws.JwsAlgorithms.HS256;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;
    private final JwtService jwtService;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper mapper,JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
        this.jwtService = jwtService;
    }

    @Override
    public UserCreatedResponse createUser(UserRequest userRequest) {

        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {

            throw new RuntimeException("The user already exists");

        }

        User user = mapper.map(userRequest, User.class);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return mapper.map(userRequest, UserCreatedResponse.class);

    }

    @Override
    public Token login(LoginRequest loginRequest) {

        Optional<User> user = Optional.of(userRepository.findByEmail(loginRequest.getUserName()).orElseThrow());

            if(!passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {

                throw new RuntimeException("The password is invalid");

            }

        return jwtService.generateToken(loginRequest);
    }
 }