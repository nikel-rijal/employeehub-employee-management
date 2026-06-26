package com.nik.employeehub.service;

import com.nik.employeehub.model.LeaveRequest;

public interface LeaveRequestService {

	void addLeaveRequest(LeaveRequest leaveRequest);
	void deleteLeaveRequest(Long id);
	void updateLeaveRequest(LeaveRequest leaveRequest);
}
