package com.dev.lembrete.service;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

    // EXPIRATION_TIME = 10 dias
    public static final long EXPIRATION_TIME = 30000;
    public static final String SECRET = "MySecret";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";

    public static void addAuthentication(HttpServletResponse response, String username) {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    public static String getTokenUsername(String token) {
        String username = Jwts.parser().setSigningKey(TokenAuthenticationService.SECRET).parseClaimsJws(token.replace(TokenAuthenticationService.TOKEN_PREFIX, "")).getBody().getSubject();
        return username != null ? username : "N/A";
    }

}