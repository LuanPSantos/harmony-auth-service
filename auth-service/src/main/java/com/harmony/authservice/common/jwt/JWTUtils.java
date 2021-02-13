package com.harmony.authservice.common.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

public class JWTUtils {

    static final long EXPIRATION_TIME_IN_MILLIS_SEC = 60_000;
    static final String SECRET = "MySecret";
    static final String AUGORITHM = "HmacSHA256";

    public static String generateJwtToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MILLIS_SEC))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static String extractSubjectFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static Date extractExpirationFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    public static boolean isValid(String token) {
        //TODO dar um jeito de realizar a falidação do hmacSha256Sign(header.payload) == signature

        String[] tokenParts = token.split("\\.");

        String signature = hmacSha256Sign(tokenParts[0] + "." + tokenParts[1]);

        if(signature == null) {
            return false;
        }

        return signature.equals(tokenParts[2]);
    }

    private static String hmacSha256Sign(String data) {
        try {
            byte[] hash = JWTUtils.SECRET.getBytes(StandardCharsets.UTF_8);

            Mac sha256Hmac = Mac.getInstance(AUGORITHM);
            SecretKeySpec secretKey = new SecretKeySpec(hash, AUGORITHM);
            sha256Hmac.init(secretKey);

            byte[] signedBytes = sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));

            return encode(signedBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
            return null;
        }
    }

    private static String encode(byte[] bytes) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
