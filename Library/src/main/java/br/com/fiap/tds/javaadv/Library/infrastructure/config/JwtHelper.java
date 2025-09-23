package br.com.fiap.tds.javaadv.Library.infrastructure.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtHelper {
    private final String SECRET = "CHAVE_SECRETA_SUPER_FUCKER_SEGURA_PACARAI";
    // private final int EXPIRATION_MS = 86400000;
    private final int EXPIRATION_MS = 20 * 60 * 60 * 1000;
    private final int REFRESH_NEW_EXPIRATION_MS = 7 * 20 * 60 * 60 * 1000;

    // cria token de acesso (curta duração).
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(SignatureAlgorithm.HS512, SECRET) // getSignKey()
                .compact();
    }

    // lê o usuário do token.
    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(getSignKey())
                .build().parseClaimsJws(token).getBody().getSubject();
    }

    // gera chave a partir da string secreta.
    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    // checa validade.
    public boolean isTokenExpired(String token) {
        return Jwts.parser().setSigningKey(getSignKey())
                .build().parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }

    // cria token de refresh (duração longa
    public String generateRefreshToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 604800000))
                .signWith(getSignKey(), SignatureAlgorithm.HS512)
                .compact();
    }
}
