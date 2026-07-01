package com.nik.employeehub.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nik.employeehub.enums.LeaveStatus;
import com.nik.employeehub.model.Employee;
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
	public boolean addLeaveRequest(LeaveRequest leaveRequest) {
		
		List<LeaveRequest> leaveList = leaveRequestRepo.findByEmployeeAndFromDateLessThanEqualAndToDateGreaterThanEqual(leaveRequest.getEmployee(), leaveRequest.getToDate(), leaveRequest.getFromDate());
		
		if(!leaveList.isEmpty()) {
			return false;
		}
		leaveRequestRepo.save(leaveRequest);
		return true;
	}

	@Override
	public void deleteLeaveRequest(Long id) {
		
		leaveRequestRepo.deleteById(id);
		
	}

	@Override
	public void updateLeaveRequest(LeaveRequest leaveRequest) {
		
		leaveRequestRepo.save(leaveRequest);
		
	}

	@Override
	public LeaveRequest leaveRequestRecord(Employee employee, LocalDate requestDate) {
		
		return leaveRequestRepo.findByEmployeeAndRequestDate(employee, requestDate).orElse(null);
	}

	@Override
	public List<LeaveRequest> findEmployeeLeaveLists(Employee employee) {
		
		return leaveRequestRepo.findByEmployee(employee);
	}

}
