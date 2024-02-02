package com.arabot.store.authserver.service;

import com.arabot.store.authserver.dto.UserCreatedResponse;
import com.arabot.store.authserver.dto.UserRequest;
import jakarta.validation.constraints.NotNull;

public interface UserService {

    UserCreatedResponse createUser(UserRequest userRequest);

}
