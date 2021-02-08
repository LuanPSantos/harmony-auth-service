package com.harmony.authservice.common.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTUtils {

    static final long EXPIRATION_TIME_IN_MILLIS_SEC = 60_000;
    static final String SECRET = "MySecret";
    static final String AUTHORIZATION_BEARER_PREFIX = "Bearer";

    public static String generateAuthorization(String subject) {
        String jwt = generateJwtToken(subject);

        return AUTHORIZATION_BEARER_PREFIX + " " + jwt;
    }

    private static String generateJwtToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MILLIS_SEC))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static String extractSubjectFromAuthorization(String authorization) {
        if (authorization != null) {
            return extractSubjectFromJwtToken(authorization.replace(AUTHORIZATION_BEARER_PREFIX, ""));
        }
        return null;
    }

    public static String extractSubjectFromJwtToken(String token) {

        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static Date extractExpirationFromAuthorization(String authorization) throws Exception {
        if (authorization != null) {
            return extractExpirationFromJwtToken(authorization.replace(AUTHORIZATION_BEARER_PREFIX, ""));
        }

        throw new Exception("Não autorizado");
    }

    private static Date extractExpirationFromJwtToken(String token) throws Exception {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

    }
}
