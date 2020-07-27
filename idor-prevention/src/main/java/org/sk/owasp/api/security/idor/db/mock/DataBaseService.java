package org.sk.owasp.api.security.idor.db.mock;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

/**
 *  Service class to mock the data flow from actual database.In real-world, one shall be using the some database interaction technology, for eg. JDBC, JPA.
 *  <br> 
 *  This class provides functionality to get records belonging to particular id.
 * 
 * 
 * @author Satish Sharma
 * 
 */
@Service
public class DataBaseService {
	
	/**
	 * Dummy Data-Store. Ideally we shall be dealing with Objects.
	 */
	private final Map<String,String> financialDataForUserId;
	
	/**
	 * initialize data for 10 user. Having ID 1-10
	 */
	public DataBaseService() {
		financialDataForUserId = new HashMap<>();

		IntStream.range(1, 10)
		.forEach(x ->{
			financialDataForUserId.put("user_"+x, "Financial Record of User "+x);
		});
	}
	
	/**
	 *  Get all the available data
	 *  
	 * @return
	 */
	public Map<String,String> getAllRecords(){
		return this.financialDataForUserId;
	}
	
	/**
	 * Dummy method to access Financial data belonging to particular ID
	 */
	public String getFinancialDaataForRecordId(String userId) {
		return this.financialDataForUserId.get(userId);
	}
	
}
