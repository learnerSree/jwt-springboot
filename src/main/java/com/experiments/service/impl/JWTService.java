package com.experiments.service.impl;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {

    private String secretKey;

    public JWTService(){

        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance( "HmacSHA256" );
            SecretKey sk = keyGenerator.generateKey();
            secretKey = Base64.getEncoder().encodeToString( sk.getEncoded() );
        } catch ( NoSuchAlgorithmException e ) {

            throw new RuntimeException( e );
        }
    }

    public String generateToken( String userName ){

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add( claims )
                .subject( userName )
                .issuedAt( new Date( System.currentTimeMillis() ) )
                .expiration( new Date( System.currentTimeMillis() + 60 * 60 * 30 ) )
                .and()
                .signWith( getKey() )
                .compact();
    }

    private Key getKey(){

        byte[] keyBytes = Decoders.BASE64.decode( secretKey );
        return Keys.hmacShaKeyFor( keyBytes );
    }

    public String extractUserName( String input ){

        return null;
    }

    public boolean validateToken( String token, UserDetails userDetails ){


        //Need to provide actual implementation
        return true;
    }

}
