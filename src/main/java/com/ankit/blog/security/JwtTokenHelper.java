package com.ankit.blog.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenHelper {

	public static final long JWT_TOKEN_VALIDITY = 5*60*60;
	
	private String secret = "jwtTokenKeyjfbhdgbhfdjhbvbjvbj";
	
	//retrieve username from jwt token
	
	public String getUsernameFromToken(String token) {
		
		return getClaimFromToken(token, Claims::getSubject);
	}

	// retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	
	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	//for retreaving any information from token we will need a sectre key
	private Claims getAllClaimsFromToken(String token) {
		
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	//check if the token has expire
	private boolean isTokenExpired(String token) {
		
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	//genetrate token from user
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*1000))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	} 
	
	
	//validate token
	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	
	
}
