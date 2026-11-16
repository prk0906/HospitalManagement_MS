package com.hospital.auth_service.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

import static io.jsonwebtoken.io.Decoder.*;

@Component
public class JWTService {

    @Value("${jwt.secret}")
    private String sceret;

    @Value("${jwt.expiration}")
    private long expiration;

    // 1. Generate signing key
    private SecretKey getSigningKey(){
        byte[] decode = Decoders.BASE64.decode(sceret);
        return Keys.hmacShaKeyFor(decode);
    }
    // 2. Generate JWT token
    public String generateToken(UserDetails userDetails, String role) {

        Date currentDate = new Date();

        Date expiryDate = new Date(
                currentDate.getTime() + expiration
        );

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("role", role)
                .issuedAt(currentDate)
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    // 3. Extract username from JWT
    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }

    // 4. Extract role from JWT
    public String extractRole(String token) {

        return extractClaim(
                token,
                claims -> claims.get("role", String.class)
        );
    }

    // 5. Extract a particular claim
    public <T> T extractClaim(
            String token,
            Function<Claims, T> claimsResolver) {

        Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    // 6. Extract all claims from JWT
    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // 7. Check whether token is expired
    private boolean isTokenExpired(String token) {

        Date expirationDate = extractClaim(
                token,
                Claims::getExpiration
        );

        return expirationDate.before(new Date());
    }

    // 8. Validate JWT
    public boolean isTokenValid(
            String token,
            UserDetails userDetails) {

        try {

            String username = extractUsername(token);

            return username.equals(userDetails.getUsername())
                    && !isTokenExpired(token);

        } catch (JwtException | IllegalArgumentException ex) {

            return false;
        }
    }

}
