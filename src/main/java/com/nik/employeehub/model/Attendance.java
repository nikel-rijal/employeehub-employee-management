package com.nik.employeehub.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.nik.employeehub.enums.AttendanceStatus;

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
@Table(name = "attendance" )
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "emp_id")
	private Employee employee;
	
	private LocalDate date;
	private LocalTime entryTime;
	private LocalTime leaveTime;
	private Long hours;
	
	@Enumerated(EnumType.STRING)
	private AttendanceStatus status;
}
