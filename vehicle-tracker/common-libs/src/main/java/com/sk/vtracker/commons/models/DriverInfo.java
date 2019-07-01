package com.sk.vtracker.commons.models;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DriverInfo implements Serializable {
	
	private int driverId;
	private String firstName;
	private String lastName;
	private int rating;

}
