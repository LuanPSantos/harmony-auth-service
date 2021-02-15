package com.harmony.authservice.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.Base64Utils;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JWTTokens {

    static final long EXPIRATION_TIME_IN_MILLIS_SEC = 60_000;
    static final String SECRET = "MySecret";

    public static <T> String generateJwtToken(String subject, Map.Entry<String, T> customField) {
        return generateJwtToken(subject, EXPIRATION_TIME_IN_MILLIS_SEC, customField);
    }

    public static <T> String generateJwtToken(String subject, Long expirationTime, Map.Entry<String, T> customField) {
        return Jwts.builder()
                .setSubject(subject)
                .claim(customField.getKey(), customField.getValue())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, getEncodedSecret())
                .compact();
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
        return Base64Utils.encodeToUrlSafeString(JWTTokens.SECRET.getBytes(StandardCharsets.UTF_8));
    }
}
