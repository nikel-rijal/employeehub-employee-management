package com.nik.employeehub.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nik.employeehub.model.Employee;
import com.nik.employeehub.service.EmployeeService;

@Configuration
public class DataInitializer {

	@Bean
	public CommandLineRunner init(EmployeeService employeeService) {
		
		return args -> {
			
			if(employeeService.findByEmail("admin@gmail.com") == null) {
				
				Employee admin = new Employee();
				admin.setEmail("admin@gmail.com");
				admin.setPassword("admin123");
				
				employeeService.createAdmin(admin);
			}
		};
	}
}
