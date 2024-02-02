package com.ezreal.types.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt工具类
 *
 * @author Ezreal
 * @Date 2023/11/18
 */
public class JwtUtils {

    private static final String secretKey = "@#.ezreal";

    private static final Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes(StandardCharsets.UTF_8));

    public static String encode(String user, Map<String, String> claimMap, int days) {

        if (claimMap == null) {
            claimMap = new HashMap<>();
        }

        Map<String, Object> header = new HashMap<>();

        Instant nowInstant = Instant.now();
        JWTCreator.Builder builder = JWT.create()
                .withHeader(header)
                .withIssuer(user)
                .withIssuedAt(nowInstant)
                .withExpiresAt(nowInstant.plus(days, ChronoUnit.DAYS));
        claimMap.forEach(builder::withClaim);
        return builder.sign(algorithm);
    }

    public static Map<String, Claim> decode(String token) {

        return JWT.require(algorithm)
                .build()
                .verify(token)
                .getClaims();
    }

    public static boolean verify(String token) {

        Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes(StandardCharsets.UTF_8));
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            verifier.verify(token);
        } catch (JWTVerificationException e) {
            return false;
        }
        return true;
    }
}
