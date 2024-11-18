package com.jbohorquez.microservices_users.infrastructure.adapters.securityconfig.jwtconfiguration;

import com.jbohorquez.microservices_users.infrastructure.output.jpa.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;

@Service
public class JwtService {
    private static final String SECRET_KEY = PRIVATE;

    public String generateToken(
            Map<String, Object> extraClaims,
            @NotNull UserEntity userDetails

    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(String.valueOf(userDetails.getId()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TIME))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generate(UserEntity userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(ROL, userDetails.getRol().getName());
        return generateToken(claims, userDetails);
    }

    public boolean isTokenValid(String token, UserEntity userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getName()));
    }

    private Claims extractAllClaims(String token) throws SignatureException {
        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException e) {
            e.printStackTrace();
            return Jwts.claims();
        }
    }
}

