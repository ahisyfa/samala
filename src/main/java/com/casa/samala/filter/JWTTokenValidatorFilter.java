package com.casa.samala.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * JWTTokenValidatorFilter
 *
 * @author Ahmad Isyfalana Amin
 */
public class JWTTokenValidatorFilter  extends OncePerRequestFilter {

    private String jwtSecretKey;

    public JWTTokenValidatorFilter(String jwtSecretKey) {
        this.jwtSecretKey = jwtSecretKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Get authorization header and validate
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Get jwt token and validate
        final String token = authorizationHeader.split(" ")[1].trim();
        if (isEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            SecretKey key = Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));

            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String username = String.valueOf(claims.get("username"));
            String authorities = (String) claims.get("authorities");
            Authentication auth = new UsernamePasswordAuthenticationToken(username, null,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));

            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid Token received!");
        }


        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals("/login");
    }

}