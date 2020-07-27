package org.sk.owasp.api.security.idor.util;

import java.util.Base64;

import org.springframework.stereotype.Service;

/**
 * <strong>FOR DEMONSTRATION ONLY, NOT TO BE USED IN PRODUCTION.</strong><br>
 *  
 * As this code is for demonstration purpose only. {@link Base64} is used to encypt-decrypt the ID's. This algorithm is not considered safe and can be
 * decoded easily. 
 * In real world scenario you shall be using more sophisticated algorithms like HMAC, RSA etc.
 * 
 * @author Satish Sharma
 *
 */
@Service
public class IdorUtility {

	// this could be externalized to configuration.
	private String saltString="S3cUr#d1Ds1L$";

	public String computeObfuscatedId(String actualIdentifier) {
		String saltedString = actualIdentifier + saltString;
		return new String(Base64.getEncoder().encode(saltedString.getBytes()));
	}

	public String resolveObfuscatedId(String obfuscatedIdentifier) {
		var decodedBit = Base64.getDecoder().decode(obfuscatedIdentifier);
		String decodedSaltedString = new String(decodedBit);
		return decodedSaltedString.replace(saltString, "");
	}
}