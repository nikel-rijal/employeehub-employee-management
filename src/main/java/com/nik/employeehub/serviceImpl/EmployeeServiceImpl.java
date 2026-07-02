package com.nik.employeehub.serviceImpl;

import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nik.employeehub.enums.EmployeeRole;
import com.nik.employeehub.model.Employee;
import com.nik.employeehub.repository.EmployeeRepository;
import com.nik.employeehub.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final PasswordEncoder passwordEncoder;
	
	private final EmployeeRepository employeeRepo;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepo, PasswordEncoder passwordEncoder) {
		this.employeeRepo = employeeRepo;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void addEmployee(Employee employee) {
		
		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		employeeRepo.save(employee);
		
	}

	@Override
	public void deleteEmployee(Long id) {
		
		employeeRepo.deleteById(id);
		
	}

	@Override
	public void updateEmployee(Employee employee) {
		
		employeeRepo.save(employee);
		
	}

	@Override
	public Employee findByEmployeeId(Long id) {
		
		return employeeRepo.findById(id).orElseThrow();
	}
	
	@Override
	public List<Employee> findEmployees() {
		
		return employeeRepo.findByRoleIn(List.of(EmployeeRole.EMPLOYEE, EmployeeRole.MANAGER));
	}

	@Override
	public Employee findByEmail(String email) {
		
		return employeeRepo.findByEmail(email).orElse(null);
	}

	@Override
	public void createAdmin(Employee employee) {
		
		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		employee.setRole(EmployeeRole.ADMIN);
		
		employeeRepo.save(employee);
	}

	@Override
	public boolean findEmailExistForOtherEmployee(String email, Long id) {
		
		return employeeRepo.findByEmailAndIdNot(email, id).isPresent();
	}

}
