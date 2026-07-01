package com.nik.employeehub.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.employeehub.model.Employee;
import com.nik.employeehub.model.LeaveRequest;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long>{

	Optional<LeaveRequest> findByEmployeeAndRequestDate(Employee employee, LocalDate requestDate);
	List<LeaveRequest> findByEmployee(Employee employee);
	List<LeaveRequest> findByEmployeeAndFromDateLessThanEqualAndToDateGreaterThanEqual(Employee employee, LocalDate toDate, LocalDate fromDate);
}
