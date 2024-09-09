package working.hotellakewood.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import working.hotellakewood.entity.User;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "79595d37784b7966532747636a3f6c7e573a28363c3724786878772e31";

    public String extractUsername(String token) {
        return extractClaim(token, Claims->Claims.getSubject());
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);

        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    //    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
//        return Jwts
//                .builder()
//                .setClaims(extraClaims)
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*24*2))
//                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        if (userDetails instanceof User user) {

            return Jwts
                    .builder()
                    .setClaims(extraClaims)
                    .setSubject(user.getEmail())  // You may use email or any other unique identifier as subject
                    .claim("userId", user.getUserId())  // Adding user ID to the payload
                    .claim("firstName", user.getFirstName())
                    .claim("role", user.getRole())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 2))
                    //.setExpiration(new Date(System.currentTimeMillis() + 1000*60*4))
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();
        } else {
            throw new IllegalArgumentException("Unsupported user details type");
        }
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
