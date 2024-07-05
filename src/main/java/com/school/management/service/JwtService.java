package com.school.management.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.school.management.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private Long jwtExpiration;

    @Value("${security.jwt.token-time}")
    private Long tokenExpiration;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(final String token, final Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
        /*Claims claims=null;
        try {
        claims= extractAllClaims(token);
        }
        catch(Exception e) {
        	throw new BadRequestServiceAlertException("user not found");
        }
        */
    }

   

    public String generateToken(final UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateRefreshToken(final UserDetails userDetails) {
        return generateRefreshToken(new HashMap<>(), userDetails);
    }

    private String generateRefreshToken(final Map<String, Object> extraClaims, final UserDetails userDetails) {
    	final User user = (User) userDetails;
        extraClaims.put("role", user.getRole());
        return buildRefreshToken(extraClaims, userDetails, tokenExpiration);
    }

    private String buildRefreshToken(final Map<String, Object> extraClaims, final UserDetails userDetails, final Long tokenExpiration) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration * 1000))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(final Map<String, Object> extraClaims, final UserDetails userDetails) {
    	final User user = (User) userDetails;
        extraClaims.put("role", user.getRole());
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    public Long getExpirationTime() {
        return jwtExpiration;
    }

    private String buildToken(final Map<String, Object> extraClaims, final UserDetails userDetails, final Long expiration) {
 
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(final String token, final UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(final String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(final String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(final String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
    	final byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}