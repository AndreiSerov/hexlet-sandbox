package org.example.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

/**
 * @author andreiserov
 */
@Component
public class JwtHelper {


    @Value("${jwt.issuer:spring_blog}")
    private String issuer;

    @Value("${jwt.expiration-sec:86400}")
    private Long expirationSec;

    @Value("${jwt.clock-skew-sec:300}")
    private Long clockSkewSec;

    @Value("${jwt.secret:ZBsCyHdqsbahw9mHoYr52LUkOd7d6h4i}")
    private String secret;

    public String generate(final Map<String, Object> attributes) {
        final Instant now = Instant.now();
        return Jwts.builder()
            .signWith(getSecretKey())
            .setIssuer(issuer)
            .setIssuedAt(Date.from(now))
            .setExpiration((expirationSec > 0) ? Date.from(now.plusMillis(expirationSec)) : null)
            .addClaims(attributes)
            .compact();
    }

    public Map<String, Object> getClaims(final String token) {
        return Jwts.parserBuilder()
            .requireIssuer(issuer)
            .setAllowedClockSkewSeconds(clockSkewSec)
            .setSigningKey(getSecretKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
}
