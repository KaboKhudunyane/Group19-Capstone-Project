package za.ac.cput.config;

import io.jsonwebtoken.*; // Importing JWT library classes
import org.springframework.security.core.userdetails.UserDetails; // Importing UserDetails for user info
import org.springframework.stereotype.Component; // Spring component annotation
import java.util.Date; // Importing Date class for expiration handling
import java.util.HashMap; // Importing HashMap for storing claims
import java.util.Map; // Importing Map interface
import java.util.function.Function; // Importing Function interface for functional programming

@Component // Indicates that this class is a Spring-managed component
public class JwtUtil {

    private String SECRET_KEY = "your_secret_key"; // Secret key used for signing the JWT

    // Extract the username from the JWT token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject); // Extract the subject (username) from claims
    }

    // Extract the expiration date from the JWT token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration); // Extract the expiration date from claims
    }

    // Extract a specific claim from the JWT token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token); // Get all claims from the token
        return claimsResolver.apply(claims); // Apply the claimsResolver function to extract the desired claim
    }

    // Parse the JWT token and extract all claims
    private Claims extractAllClaims(String token) {
        return Jwts.parser() // Create a JWT parser
                .setSigningKey(SECRET_KEY) // Set the secret key used for signing
                .parseClaimsJws(token) // Parse the token and retrieve claims
                .getBody(); // Get the claims body
    }

    // Check if the JWT token is expired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date()); // Check if the expiration date is before the current date
    }

    // Generate a new JWT token for the given user details
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(); // Create a claims map (can be used to store additional info)
        return createToken(claims, userDetails.getUsername()); // Generate token with claims and username
    }

    // Create a JWT token with specified claims and subject
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder() // Start building the JWT
                .setClaims(claims) // Set the claims
                .setSubject(subject) // Set the subject (username)
                .setIssuedAt(new Date(System.currentTimeMillis())) // Set the issued date
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Set expiration to 10 hours
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Sign the token using HS256 algorithm and the secret key
                .compact(); // Compact the token into a string
    }

    // Validate the token by checking its username and expiration
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token); // Extract username from the token
        // Check if the extracted username matches the userDetails username and if the token is not expired
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
