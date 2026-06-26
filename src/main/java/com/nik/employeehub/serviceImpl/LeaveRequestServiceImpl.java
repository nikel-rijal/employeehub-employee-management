package com.nik.employeehub.serviceImpl;

import org.springframework.stereotype.Service;

import com.nik.employeehub.model.LeaveRequest;
import com.nik.employeehub.repository.LeaveRequestRepository;
import com.nik.employeehub.service.LeaveRequestService;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {
	
	private final LeaveRequestRepository leaveRequestRepo;
	
	public LeaveRequestServiceImpl(LeaveRequestRepository leaveRequestRepo) {
		this.leaveRequestRepo = leaveRequestRepo;
	}

	@Override
	public void addLeaveRequest(LeaveRequest leaveRequest) {
		
		leaveRequestRepo.save(leaveRequest);
		
	}

	@Override
	public void deleteLeaveRequest(Long id) {
		
		leaveRequestRepo.deleteById(id);
		
	}

	@Override
	public void updateLeaveRequest(LeaveRequest leaveRequest) {
		
		leaveRequestRepo.save(leaveRequest);
		
	}

}
