package com.nik.employeehub.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.employeehub.enums.EmployeeRole;
import com.nik.employeehub.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Optional<Employee> findByEmail(String email);
	List<Employee> findByRoleIn(List<EmployeeRole> role);
	Optional<Employee> findByEmailAndIdNot(String email, Long id);
}
