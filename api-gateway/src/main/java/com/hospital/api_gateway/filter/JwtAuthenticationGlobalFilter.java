package com.hospital.api_gateway.filter;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;

@Component
public class JwtAuthenticationGlobalFilter implements GlobalFilter, Ordered {
    @Value("${jwt.secret}")
    private String secret;

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        // Allow registration and login without JWT
        if(path.startsWith("/auth/")){
            return chain.filter(exchange);
        }
        // Protect only business APIs
        boolean protectedPath =  path.startsWith("/patients/")
                || path.equals("/patients")
                || path.startsWith("/doctor/")
                || path.equals("/doctor")
                || path.startsWith("/appointments/")
                || path.equals("/appointments");

        if(!protectedPath)
            return chain.filter(exchange);
        String authHeader = exchange.getRequest().getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);
        if(authHeader == null || !authHeader.startsWith("Bearer "))
            return unauthorized(exchange);
        String token = authHeader.substring(7);
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);

            return chain.filter(exchange);

        } catch (JwtException | IllegalArgumentException ex) {
            return unauthorized(exchange);
        }
    }
    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse()
                .setStatusCode(HttpStatus.UNAUTHORIZED);

        return exchange.getResponse().setComplete();
    }
    @Override
    public int getOrder() {
        return 0;
    }
}
