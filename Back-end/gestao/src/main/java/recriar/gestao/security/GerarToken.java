package recriar.gestao.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import recriar.gestao.entities.Usuario;


@Component
public class GerarToken {

    private final SecretKey secretKey;
    private final long expiration = 3600000; // 1 hora

    public GerarToken(@Value("${jwt.secret}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String gerarToken(Usuario obj) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", obj.getId());
        claims.put("email", obj.getEmail());
        claims.put("tipo", obj.getTipo());

        Date issuedAt = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(System.currentTimeMillis() + expiration);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(obj.getEmail())
                .setIssuedAt(issuedAt)
                .setExpiration(expirationDate)
                .signWith(secretKey)
                .compact();
    }
    
    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
    
    public boolean tokenValido(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            Date expirationDate = claims.getExpiration();
            return expirationDate.after(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}

