package com.harmony.authservice.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.Base64Utils;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JWTToken {

    static final long EXPIRATION_TIME_IN_MILLIS_SEC = 60_000;
    static final String SECRET = "MySecret";

    public static String generateJwtToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MILLIS_SEC))
                .signWith(SignatureAlgorithm.HS512, getEncodedSecret())
                .compact();
    }

    public static String extractSubjectFromJwtToken(String token) {
        return getClaims(token)
                .getBody()
                .getSubject();
    }

    public static boolean isExpired(String token) {
        Jws<Claims> claims = getClaims(token);

        return new Date().after(claims.getBody().getExpiration());
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
        return Base64Utils.encodeToUrlSafeString(JWTToken.SECRET.getBytes(StandardCharsets.UTF_8));
    }
}
