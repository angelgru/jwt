package com.example.jwt;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private String secret = "superSecret";
    int expiration_time = 10 * 60;
    private String token_prefix = "Bearer ";
    private String header_string = "Authorization";

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {



        String header = request.getHeader(header_string);

        if(header == null || !header.startsWith(token_prefix)) {
            log.error("OBID ZA AVTORIZACIJA");
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                getAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(header_string);
        if(token != null) {
            String user = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token.replace(token_prefix, ""))
                    .getBody()
                    .getSubject();

            if(user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }

            return null;
        }
        return null;
    }
}
