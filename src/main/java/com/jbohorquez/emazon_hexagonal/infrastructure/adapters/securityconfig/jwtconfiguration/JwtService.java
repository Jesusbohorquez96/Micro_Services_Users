package com.jbohorquez.emazon_hexagonal.infrastructure.adapters.securityconfig.jwtconfiguration;

import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.PRIVATE;
import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.TIME;

@Service
public class JwtService {
    private static final String SECRET_KEY = PRIVATE;

    public String getToken(UserEntity user){
        return generateToken(new HashMap<>(),user);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            @NotNull UserEntity userDetails

    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getEmail())
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
        claims.put("role", userDetails.getRole());
        claims.put("id", userDetails.getId());
        return generateToken(claims, userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()));
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

