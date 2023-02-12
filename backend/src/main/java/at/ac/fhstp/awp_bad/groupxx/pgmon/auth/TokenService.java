package at.ac.fhstp.awp_bad.groupxx.pgmon.auth;

import at.ac.fhstp.awp_bad.groupxx.pgmon.entities.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.security.Key;
import java.util.Date;

@Service
public class TokenService {
    @Value("${values.jwtKey}")
    private String KEY;

    public String generateToken(User user){
        Key key = Keys.hmacShaKeyFor(KEY.getBytes());
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .claim("userId", user.getId())
                .claim("name", user.getUsername())
                .signWith(key)
                .compact();
    }

    public User parseToken(String jwt) throws JwtException{
        Jws<Claims> claims;
        claims = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes())).build().parseClaimsJws(jwt);

        User user = new User();
        user.setEmail(claims.getBody().getSubject());
        user.setUsername(claims.getBody().get("name", String.class));
        user.setId(claims.getBody().get("userId", Integer.class));

        return user;
    }

}
