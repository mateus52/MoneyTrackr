package com.MoneyTrackr.MoneyTrackr.security.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.MoneyTrackr.MoneyTrackr.dto.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${Money.Trackr.jwtSecret}")
	private String jwtSecret;
	
	@Value("${Money.Trackr.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	public String generateTokenFromUserDetailsImpl(UserDetailsImpl userDetailsServiceImpl) {
		return Jwts.builder().setSubject(userDetailsServiceImpl.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
				.signWith(getSigninKey(), SignatureAlgorithm.HS512).compact();
	}
	
	public Key getSigninKey() {
		SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
		return key;
	}
	
	public String getUsernameToken(String token) {
		return Jwts.parser().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(getSigninKey()).build().parse(authToken);
			return true;
		}catch (MalformedJwtException e) {
			System.out.println("TOKEN INVALIDO " + e.getMessage() );
		}catch(ExpiredJwtException e ) {
			System.out.println("TOKEN EXPIRADO " + e.getMessage() );
		}catch(UnsupportedJwtException e ) {
			System.out.println("TOKEN NAO SUPORTADO " + e.getMessage() );
		}catch(IllegalArgumentException e ) {
			System.out.println("TOKEN ARGUMENTO INVALIDO " + e.getMessage() );
		}
		
		return false;
		
	}
}
