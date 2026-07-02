package com.nik.employeehub.service;

import java.util.List;

import com.nik.employeehub.model.Employee;

public interface EmployeeService {

	void addEmployee(Employee employee);
	void deleteEmployee(Long id);
	void updateEmployee(Employee employee);
	Employee findByEmployeeId(Long id);
	List<Employee> findEmployees();
	Employee findByEmail(String email);
	void createAdmin(Employee employee);
	boolean findEmailExistForOtherEmployee(String email, Long id);
}
