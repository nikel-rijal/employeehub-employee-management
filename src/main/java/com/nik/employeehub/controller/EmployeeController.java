package com.nik.employeehub.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nik.employeehub.dto.LeaveRequestDTO;
import com.nik.employeehub.enums.LeaveStatus;
import com.nik.employeehub.model.Attendance;
import com.nik.employeehub.model.Employee;
import com.nik.employeehub.model.LeaveRequest;
import com.nik.employeehub.service.AttendanceService;
import com.nik.employeehub.service.EmployeeService;
import com.nik.employeehub.service.LeaveRequestService;

import jakarta.validation.Valid;

@Controller
public class EmployeeController {
	
	private final EmployeeService empService;
	private final AttendanceService attendanceService;
	private final LeaveRequestService leaveRequestService;
	
	public EmployeeController(EmployeeService empService, AttendanceService attendanceService, LeaveRequestService leaveRequestService) {
		this.empService = empService;
		this.attendanceService = attendanceService;
		this.leaveRequestService = leaveRequestService;
	}

	@GetMapping("/employee/dashboard")
	public String getEmployeeDashboard() {
		
		return "EmployeeDashboard";
	}
	
	@GetMapping("/employee/profile")
	public String getProfile(Model model) {
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Employee employee = empService.findByEmail(email);
		model.addAttribute("activeEmployee", employee);
		return "EmployeeProfile";
	}
	
	@PostMapping("/employee/profile")
	public String postProfile() {
		
		return "redirect:/employee/dashboard";
	}
	
	@GetMapping("/employee/attendance")
	public String  getMarkAttendance(Model model) {
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Employee employee = empService.findByEmail(email);
		
		Attendance attendance = attendanceService.attendanceRecord(employee, LocalDate.now());
		
		model.addAttribute("attendance", attendance);
		model.addAttribute("date", LocalDate.now());
		return "EmployeeAttendance";
		
	}
	
	@PostMapping("/employee/attendance/entry")
	public String postMarkEntry() {
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Employee employee = empService.findByEmail(email);
		
		attendanceService.entryAttendance(employee);
		return "redirect:/employee/attendance";
	}
	
	@PostMapping("/employee/attendance/exit")
	public String postMarkExit() {
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Employee employee = empService.findByEmail(email);
		
		attendanceService.exitAttendance(employee);
		return "redirect:/employee/attendance";
	}
	
	@GetMapping("/employee/attendance/history")
	public String getAttendanceHistory(Model model) {
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Employee employee = empService.findByEmail(email);
		List<Attendance> alist = attendanceService.findAttendanceByEmployee(employee);
		
		model.addAttribute("alist", alist);
		return "EmployeeAttendanceHistory";
	}
	
	@GetMapping("/employee/leave/request")
	public String  getLeaveRequest(Model model) {
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Employee employee = empService.findByEmail(email);
		LeaveRequest leaveRequest = leaveRequestService.leaveRequestRecord(employee, LocalDate.now());

		model.addAttribute("leaveRequest", leaveRequest);
		model.addAttribute("leave", new LeaveRequest());
		return "EmployeeLeaveRequest";
	}
	
	@PostMapping("/employee/leave/request")
	public String postLeaveRequest(@Valid @ModelAttribute("leave") LeaveRequestDTO dto, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			
			return "EmployeeLeaveRequest";
		}
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		Employee employee = empService.findByEmail(email);
		
		LeaveRequest leaveRequest = leaveRequestService.leaveRequestRecord(employee, LocalDate.now());
		
		if(leaveRequest == null) {
			
			LeaveRequest request = new LeaveRequest();
			
			request.setEmployee(employee);
			request.setRequestDate(LocalDate.now());
			request.setFromDate(dto.getFromDate());
			request.setToDate(dto.getToDate());
			request.setReason(dto.getReason());
			request.setStatus(LeaveStatus.PENDING);
			
			boolean saved = leaveRequestService.addLeaveRequest(request);
			if(!saved) {
				model.addAttribute("error", "Your date overlap with an existing request date!!");
				return "EmployeeLeaveRequest";
			}
		}
		
		return "redirect:/employee/leave/request";
	}
	
	@GetMapping("/employee/leave/list")
	public String getLeaveList(Model model) {
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Employee employee = empService.findByEmail(email);
		
		List<LeaveRequest> leaveList =  leaveRequestService.findEmployeeLeaveLists(employee);
		model.addAttribute("leaveList", leaveList);
		return "EmployeeLeaveRequestList";
	}
}
