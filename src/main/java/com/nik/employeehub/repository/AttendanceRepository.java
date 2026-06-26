package com.nik.employeehub.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.employeehub.model.Attendance;
import com.nik.employeehub.model.Employee;

public interface AttendanceRepository extends JpaRepository<Attendance, Long>{

	Optional<Attendance> findByEmployeeAndDate(Employee employee, LocalDate date);
	List<Attendance> findByEmployee(Employee employee);
	
}
