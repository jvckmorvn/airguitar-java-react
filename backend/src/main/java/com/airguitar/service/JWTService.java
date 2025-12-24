package com.airguitar.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JWTService {

    public String generate(UUID userId) {
        String secret = "super-secret-key";

        return Jwts.builder()
            .setSubject(userId.toString())
            .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
            .compact();
    }

}
