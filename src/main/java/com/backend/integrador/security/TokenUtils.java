package com.backend.integrador.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

public class TokenUtils {

  private final static String TOKEN_ACCESS_TOKEN = "ZAmNLqjKMNXDfMZVKWSVVvT7CL9DuMhnEW6mAcHn";
// generados aleatorio para firmar el token esto no debe compartise

  private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 3600L; // para convertirlo en milis

  public static String createToken(String nombre, String email) { // crear el token
    long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
    Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

    Map<String, Object> extra = Map.of("name", nombre); // esto se va con el token para crearlo
    return Jwts
        .builder()
        .setSubject(email) //sujeto es decir a que correo se le asigna el token
        .setExpiration(expirationDate) //expiracion
        .addClaims(extra)// asi es como se denominan a la claves
        .signWith(Keys.hmacShaKeyFor(TOKEN_ACCESS_TOKEN.getBytes())).compact(); // se firma, con todo esto se obtinee la generacion de un token
  }

  public static UsernamePasswordAuthenticationToken getAuthentication(String token) { // genera el usuario a partir del token
    try {
      Claims claims = Jwts.parserBuilder() // la idea es hacer un proceso inverso a de crear el token
          .setSigningKey(TOKEN_ACCESS_TOKEN.getBytes()) // la clave
          .build()
          .parseClaimsJws(token)
          .getBody();

      String email = claims.getSubject();
      return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
    } catch (JwtException e) {
      return null;
    }
  }
}