package com.nik.employeehub.model;

import java.time.LocalDate;

import com.nik.employeehub.enums.EmployeeRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstname;
	private String lastname;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	private String address;
	private String phoneNumber;
	private LocalDate joinedDate;
	
	@Enumerated(EnumType.STRING)
	private EmployeeRole role;
	
	@ManyToOne
	@JoinColumn(name = "dept_id")
	private Department department;
	

}
