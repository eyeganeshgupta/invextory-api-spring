package com.invextory.security;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    private static final long EXPIRATION_TIME_IN_MILLSEC = 1000L * 60L * 60L * 24L * 30L * 6L;

    private SecretKey key;
    private final String secretKey;

    public JwtUtils(Dotenv dotenv) {
        this.secretKey = dotenv.get("JWT_SECRET_KEY");
        logger.info("JwtUtils instantiated with environment-provided secret key.");
    }

    @PostConstruct
    private void init() {
        byte[] keyByte = secretKey.getBytes(StandardCharsets.UTF_8);
        this.key = new SecretKeySpec(keyByte, "HmacSHA256");
        logger.info("SecretKey for JWT initialized using HmacSHA256.");
    }

    public String generateToken(String email) {
        String token = Jwts.builder()
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MILLSEC))
                .signWith(key)
                .compact();
        logger.info("JWT token generated for email: {}", email);
        return token;
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        logger.info("Extracting claims from token.");
        return claimsResolver.apply(Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload());
    }

    public String getUsernameFromToken(String token) {
        String username = extractClaims(token, Claims::getSubject);
        logger.info("Extracted username from token: {}", username);
        return username;
    }

    private boolean isTokenExpired(String token) {
        boolean expired = extractClaims(token, Claims::getExpiration).before(new Date());
        logger.info("Token expiration checked: {}", expired ? "Expired" : "Valid");
        return expired;
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        boolean isValid = username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        logger.info("Token validity check for user '{}': {}", userDetails.getUsername(), isValid ? "Valid" : "Invalid");
        return isValid;
    }
}
