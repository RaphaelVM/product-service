package com.drossdrop.productservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.ArrayList;

@Component
public class JwtUtil {


    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";


    private Claims extractClaims(String token) {
        Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);

        return jws.getBody();
    }

    public ArrayList<String> getRoleFromClaims(String token) {
        Claims claims = extractClaims(token);
        String role = claims.get("roles", String.class);
        return new ArrayList<String>() {{
            add(role);
        }};
    }

    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
