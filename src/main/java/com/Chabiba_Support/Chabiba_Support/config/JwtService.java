package com.Chabiba_Support.Chabiba_Support.config;

import com.Chabiba_Support.Chabiba_Support.models.Personne;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.sql.Date;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

	private  static final String SECRET_KEY = "68576D5A7134743777217A25432A462D4A614E645266556A586E327235753878";

	public String extractUsername(String token){

		return extractClaim(token, Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}

	public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		Personne personne = (Personne) userDetails; // Cast UserDetails to Personne
		Map<String, Object> claims = new HashMap<>();

		// Add Personne attributes to the claims
		claims.put("idPersonne", personne.getIdPersonne());
		claims.put("nom", personne.getNom());
		claims.put("prenom", personne.getPrenom());
		claims.put("numTel", personne.getNumTel());
		claims.put("email", personne.getEmail());
		claims.put("role", personne.getRole());
		claims.put("entreprise", personne.getEmployee());
		claims.put("pic",personne.getProfilPicture());
		// Add more Personne attributes as needed

		// Add extra claims if provided
		claims.putAll(extraClaims);

		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getSignInkey(), SignatureAlgorithm.HS256)
				.compact();
	}

	public boolean isTokenValid(String token, UserDetails userDetails){
		final String username =extractUsername(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		return  extractExpiration(token).before(new java.util.Date());
	}

	private java.util.Date extractExpiration(String token) {
		return  extractClaim(token, Claims::getExpiration);
	}

	private Claims extractAllClaims(String token){
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInkey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	private Key getSignInkey() {
		byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}


}
