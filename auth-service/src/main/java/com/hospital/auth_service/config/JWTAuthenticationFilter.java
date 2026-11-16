package com.hospital.auth_service.config;

import com.hospital.auth_service.service.CustomeUserDetailService;
import com.hospital.auth_service.service.JWTService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JWTService jwtService;
    @Autowired
    private CustomeUserDetailService userDetailService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        // 1. Check whether Authorization header contains Bearer token
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        // 2. Extract token
        String token = authHeader.substring(7);
        try{
            // 3. Extract username from token
            String userName = jwtService.extractUsername(token);

            // 4. Authenticate only if not already authenticated
            if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                // 5. Load user from database
                UserDetails userDetails = userDetailService.loadUserByUsername(userName);
                // 6. Validate token and check user is enabled
                if(userDetails.isEnabled() && jwtService.isTokenValid(token,userDetails)){
                    // 7. Create authentication object
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // 8. Store authenticated user in SecurityContext
                    SecurityContextHolder.getContext()
                            .setAuthentication(authentication);
                }
            }
        }catch (JwtException | IllegalArgumentException exception){
            SecurityContextHolder.clearContext();
        }
        // 9. Continue filter chain
        filterChain.doFilter(request,response);
    }
}
