package org.example.spring_security.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@Data
public class JwtService {
    @Value("${jwt.secretKey}")
    private String secretKey = "secretKey";

    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .signWith(signInKey())
                .issuedAt(new Date())
                .issuer("miu.edu")
                .expiration(new Date(new Date().getTime()+1000*60*60*24))
                .subject(userDetails.getUsername())
                .claim("authorities", populateAuthorities(userDetails.getAuthorities()))
                .compact();

    }

    private SecretKey signInKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }


}
