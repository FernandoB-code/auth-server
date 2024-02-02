package com.arabot.store.authserver.service.impl;

import com.arabot.store.authserver.dto.UserCreatedResponse;
import com.arabot.store.authserver.dto.UserRequest;
import com.arabot.store.authserver.model.Role;
import com.arabot.store.authserver.model.User;
import com.arabot.store.authserver.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.arabot.store.authserver.repository.UserRepository;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    public UserCreatedResponse createUser(UserRequest userRequest) {

        if (userRepository.findByEmail(userRequest.getEmail()) != null) {

            throw new RuntimeException("The user already exists");

        }

        User user = mapper.map(userRequest, User.class);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return mapper.map(userRequest, UserCreatedResponse.class);

    }
}

