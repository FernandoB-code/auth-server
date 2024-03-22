package com.arabot.store.authserver.service;

import com.arabot.store.authserver.dto.LoginRequest;
import com.arabot.store.authserver.dto.Token;
import com.arabot.store.authserver.dto.UserCreatedResponse;
import com.arabot.store.authserver.dto.UserRequest;
import jakarta.validation.constraints.NotNull;

public interface UserService {

    UserCreatedResponse createUser(UserRequest userRequest);

    Token login(LoginRequest loginRequest);
}
