package com.dev.lembrete.security;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.lembrete.service.TokenAuthenticationService;
import com.dev.lembrete.service.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter
{
    private final UserService userService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserService userService){
        super(authenticationManager);
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        String header = request.getHeader(TokenAuthenticationService.HEADER_STRING);
        if(header == null || !header.startsWith(TokenAuthenticationService.TOKEN_PREFIX))
        {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest req)
    {
        String token = req.getHeader(TokenAuthenticationService.HEADER_STRING);
        if(token == null) return null;
        String username = Jwts.parser().setSigningKey(TokenAuthenticationService.SECRET).parseClaimsJws(token.replace(TokenAuthenticationService.TOKEN_PREFIX, "")).getBody().getSubject();

        UserDetails userDetails = userService.loadUserByUsername(username);
        System.out.println("veio 222222222222222222222;"+ userDetails);
        return username != null ? new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities()) : null;
    }
}