package com.ubagroup.switchemv.service;

import com.ubagroup.switchemv.model.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key =
            Keys.hmacShaKeyFor("VERY_SECRET_256_BIT_KEY_CHANGE_ME".getBytes());

    public String generate(AppUser user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role",user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(key)
                .compact();
    }

    public boolean isValid(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
