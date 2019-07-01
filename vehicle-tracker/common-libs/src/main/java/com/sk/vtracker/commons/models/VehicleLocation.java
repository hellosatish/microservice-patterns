package com.sk.vtracker.commons.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @author satish-s
 *  
 *  Model class for location of a vehicle
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleLocation implements Serializable{
	
	private int vehicleId;	//unique id for each vehicle
	private boolean online;	//weather vehilce is online or offline
	private boolean available;	// is vehilcle ready to take bookings or not
	private double latitude;
	private double longitude;
	public VehicleLocation(boolean online, boolean available, double latitude, double longitude) {
		super();
		this.online = online;
		this.available = available;
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
