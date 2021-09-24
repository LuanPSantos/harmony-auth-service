package com.harmony.authservice.domain.token.model;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.Base64Utils;

import java.util.Date;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

public class JWTTokens {

    static final String SECRET = "MySecret";

    public static <T> Token generateJwtToken(String subject, Long timeToLive, Map.Entry<String, T> customField) {
        return new Token(
                Jwts.builder()
                        .setSubject(subject)
                        .claim(customField.getKey(), customField.getValue())
                        .setExpiration(new Date(System.currentTimeMillis() + timeToLive))
                        .signWith(SignatureAlgorithm.HS512, getEncodedSecret())
                        .compact()
        );
    }

    public static Token generateJwtToken(Long timeToLive) {
        return new Token(
                Jwts.builder()
                        .setExpiration(new Date(System.currentTimeMillis() + timeToLive))
                        .signWith(SignatureAlgorithm.HS512, getEncodedSecret())
                        .compact()
        );
    }

    public static String extractSubjectFromJwtToken(String token) {
        return getClaims(token)
                .getBody()
                .getSubject();
    }

    public static String extractCustomFieldFromJwtToken(String token, String key) {
        return getClaims(token).getBody().get(key, String.class);
    }

    public static void checkTokenSignature(String token) {
        getClaims(token);
    }

    private static Jws<Claims> getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getEncodedSecret())
                .parseClaimsJws(token);
    }

    private static String getEncodedSecret() {
        return Base64Utils.encodeToUrlSafeString(JWTTokens.SECRET.getBytes(UTF_8));
    }
}
