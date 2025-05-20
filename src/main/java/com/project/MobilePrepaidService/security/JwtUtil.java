package com.project.MobilePrepaidService.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private static final String SECRET = "MYSECRETKEYmysecretkeyMYSECRETKEYmysecretkey";
	private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

	// Generate JWT Token+
	public String generateToken(String username) {
		return Jwts.builder().subject(username).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiry
				.signWith(SECRET_KEY).compact();
	}

//	Validate Token
	public boolean validateToken(String Token) {
		try {
			Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(Token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

//	Extracts Username from Token

	public String extractUsername(String Token) {
		Claims claims = Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(Token).getPayload();
		return claims.getSubject();
	}
}
