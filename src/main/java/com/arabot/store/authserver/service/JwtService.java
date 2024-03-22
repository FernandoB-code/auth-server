package com.arabot.store.authserver.service;

import com.arabot.store.authserver.dto.LoginRequest;
import com.arabot.store.authserver.dto.Token;

public interface JwtService {

    Token generateToken(LoginRequest request);
}
