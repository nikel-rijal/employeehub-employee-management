package com.nik.employeehub.serviceImpl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nik.employeehub.model.Employee;
import com.nik.employeehub.repository.EmployeeRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final EmployeeRepository employeeRepo;

	public CustomUserDetailsService(EmployeeRepository employeeRepo) {
		this.employeeRepo = employeeRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Employee employee = employeeRepo.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Employee not found!"));

		return User.withUsername(employee.getEmail())
					.password(employee.getPassword())
					.authorities(employee.getRole().name())
					.build();
	}

}
