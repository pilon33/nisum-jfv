package com.nisum.test.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

	private static String RESPONSE_OBJECT_JWT;

	private static String SECRET_KEY;

	private static String EXPIRATION_TIME;

	/**
	 * Generates the token for the authenticated user base on {@link AuthTokenData}.
	 * 
	 * @param authTokenData
	 * @return String
	 */
	public static String generateTokenAuth(AuthTokenData authTokenData) {
		Map<String, Object> mapDataToken = new HashMap<>();
		mapDataToken.put(RESPONSE_OBJECT_JWT, authTokenData);

		Date expirationDate = Date.from(LocalDateTime.now().plusHours(Long.valueOf(EXPIRATION_TIME))
				.atZone(ZoneId.systemDefault()).toInstant());
		return Jwts.builder().setSubject(authTokenData.getName()).setClaims(mapDataToken)
				.setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, SECRET_KEY).setNotBefore(new Date())
				.compact();

	}

	@Value("${app.jwt.response-object-identifier}")
	private void setJwtObjectResponse(String jwtResponseObject) {
		RESPONSE_OBJECT_JWT = jwtResponseObject;
	}

	@Value("${app.jwt.secret-key}")
	private void setSecretKey(String secretKey) {
		SECRET_KEY = secretKey;
	}

	@Value("${app.jwt.expiration-time}")
	private void timeExpiration(String expirationTime) {
		EXPIRATION_TIME = expirationTime;
	}

}
