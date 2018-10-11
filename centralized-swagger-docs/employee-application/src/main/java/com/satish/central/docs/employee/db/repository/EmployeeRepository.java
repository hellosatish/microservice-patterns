package com.satish.central.docs.employee.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satish.central.docs.employee.db.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
