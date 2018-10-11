package com.satish.central.docs.employee.db.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int employeeId;
	
	private String employeeFirstName;
	private String employeeLastName;
	private String email;
}
