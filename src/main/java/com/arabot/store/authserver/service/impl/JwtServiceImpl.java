package com.arabot.store.authserver.service.impl;

import com.arabot.store.authserver.dto.LoginRequest;
import com.arabot.store.authserver.dto.Token;
import com.arabot.store.authserver.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;


    @Override
    public Token generateToken(LoginRequest loginRequest) {

        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000 * 60 * 60); // 1 hour expiration


        byte[] secretKeyBytes = this.secretKey.getBytes();
        SecretKey key = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS256.getJcaName());

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", loginRequest.getUserName());


        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return Token.builder().token(token).build();
    }

}
