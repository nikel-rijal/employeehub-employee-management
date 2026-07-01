package com.nik.employeehub.service;

import java.time.LocalDate;
import java.util.List;

import com.nik.employeehub.model.Employee;
import com.nik.employeehub.model.LeaveRequest;

public interface LeaveRequestService {

	boolean addLeaveRequest(LeaveRequest leaveRequest);
	void deleteLeaveRequest(Long id);
	void updateLeaveRequest(LeaveRequest leaveRequest);
	LeaveRequest leaveRequestRecord(Employee employee, LocalDate requestDate);
	List<LeaveRequest> findEmployeeLeaveLists(Employee employee);
}
