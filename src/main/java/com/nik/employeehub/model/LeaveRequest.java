package com.nik.employeehub.model;

import java.time.LocalDate;

import com.nik.employeehub.enums.LeaveStatus;

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
@Table(name = "leave_request")
public class LeaveRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "emp_id")
	private Employee employee;
	
	private LocalDate requestDate;
	private LocalDate fromDate;
	private LocalDate toDate;
	private String reason;
	
	@Enumerated(EnumType.STRING)
	private LeaveStatus status;
	
	@ManyToOne
	@JoinColumn(name = "reviewed_by")
	private Employee reviewedBy;
}
