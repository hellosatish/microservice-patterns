package com.sk.vtracker.commons.models;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author satish-s
 *
 * Vehicle details 
 */
@Data
@NoArgsConstructor
public class Vehicle implements Serializable{
	
	private int vehicleId;
	private String vehilceNumber;
	//Current assumption is all the vehicles are CAR.
	private String vehicleType ="CAR";

}
