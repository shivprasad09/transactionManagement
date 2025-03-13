package com.example.onlinebanking.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * JwtUtil is a utility class for handling JSON Web Tokens (JWT) in the application.
 * It provides methods for generating, parsing, and validating JWT tokens, as well as
 * extracting information such as usernames and expiration dates from tokens.
 *
 * <p>This class uses the {@link Jwts} library to handle JWT operations
 * and is integrated with Spring Security for user authentication and authorization.
 *
 * <p>Key features:
 * <ul>
 *   <li>Generates JWT tokens for authenticated users.</li>
 *   <li>Extracts claims such as username and expiration date from tokens.</li>
 *   <li>Validates JWT tokens against user details.</li>
 *   <li>Uses a secret key for signing and verifying tokens.</li>
 * </ul>
 *
 * @author [Your Name]
 * @version 1.0
 * @since [Date]
 * @see Jwts
 * @see UserDetails
 * @see Component
 */
@Component
public class JwtUtil {

    private String SECRET_KEY = "secret";

    /**
     * Extracts the username from the provided JWT token.
     *
     * @param token the JWT token from which to extract the username.
     * @return the username (subject) from the token.
     */
    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts the expiration date from the provided JWT token.
     *
     * @param token the JWT token from which to extract the expiration date.
     * @return the expiration date from the token.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts a specific claim from the provided JWT token using the given claims resolver function.
     *
     * @param token the JWT token from which to extract the claim.
     * @param claimsResolver the function to resolve the desired claim from the token's claims.
     * @return the resolved claim.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extracts all claims from the provided JWT token.
     *
     * @param token the JWT token from which to extract the claims.
     * @return the claims contained in the token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    /**
     * Generates a JWT token for the provided user details.
     *
     * @param userDetails the user details for which to generate the token.
     * @return the generated JWT token.
     */
    public String generateToken(CustomUserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * Creates a JWT token with the provided claims and subject.
     *
     * @param claims the claims to include in the token.
     * @param subject the subject (e.g., username) to include in the token.
     * @return the created JWT token.
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    /**
     * Validates the provided JWT token against the user details.
     *
     * @param token the JWT token to validate.
     * @param userDetails the user details to validate against.
     * @return true if the token is valid for the user, false otherwise.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Checks if the provided JWT token is expired.
     *
     * @param token the JWT token to check.
     * @return true if the token is expired, false otherwise.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}