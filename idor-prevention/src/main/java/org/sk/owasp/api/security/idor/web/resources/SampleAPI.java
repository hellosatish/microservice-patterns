package org.sk.owasp.api.security.idor.web.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.sk.owasp.api.security.idor.db.mock.DataBaseService;
import org.sk.owasp.api.security.idor.util.IdorUtility;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * <strong>FOR DEMONSTRATION ONLY, NOT TO BE USED IN PRODUCTION.</strong><br>
 * Dummy controller exposing API to get data based on ID's
 * 
 * 
 * @author Satish Sharma
 *
 */
@RestController
@RequiredArgsConstructor // auto-generate constructor
public class SampleAPI {
	
	private final DataBaseService dbService;
	private final IdorUtility idorUtil;
	
	
	@GetMapping(value = "/getdata-IDOR-vulnerable/{user-id}")
	public String getDataForIDUnsecured(@PathVariable("user-id") String userId) {
		return dbService.getFinancialDaataForRecordId(userId);
	}
	
	@GetMapping(value ="/get-identifiers")
	public List<String> getUserIDs(){
		 return dbService.getAllRecords().keySet().stream().collect(Collectors.toList());
	}
	
	// --------------- Safer API's --------------- // 
	
	@GetMapping(value = "/getdata-IDOR-secured/{user-id}")
	public String getDataForID_IDOR_SAFE(@PathVariable("user-id") String obfuscatedIdentifier) {
		String actualIdentifier = idorUtil.resolveObfuscatedId(obfuscatedIdentifier);
		return dbService.getFinancialDaataForRecordId(actualIdentifier);
	}
		
	@GetMapping(value ="/get-identifiers-IDOR-secured")
	public List<String> getObfuscatedUserIDs(){
		 return dbService.getAllRecords().keySet().stream().map( userId -> {
			 return idorUtil.computeObfuscatedId(userId);
		 }).collect(Collectors.toList());
	}
	
}
