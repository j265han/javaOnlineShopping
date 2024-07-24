package org.example.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.Map;
import java.util.Optional;


public class JwtUtils {
    static Algorithm algorithm = Algorithm.HMAC256("org.example");

    public static String generateToken(Map<String, Object> claims) {

        String token = JWT.create()
                .withClaim("claims", claims)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .sign(algorithm);
        return token;

    }

    public static Map<String, Object> parseToken(String token) {

//        return JWT.decode(token).getClaim("claims").asMap();
        return JWT.require(algorithm).build().verify(token).getClaim("claims").asMap();

    }
}

