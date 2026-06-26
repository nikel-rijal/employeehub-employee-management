package com.nik.employeehub.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.nik.employeehub.model.Attendance;
import com.nik.employeehub.model.Employee;
import com.nik.employeehub.service.AttendanceService;
import com.nik.employeehub.service.EmployeeService;

@Controller
public class EmployeeController {
	
	private final EmployeeService empService;
	private final AttendanceService attendanceService;
	
	public EmployeeController(EmployeeService empService, AttendanceService attendanceService) {
		this.empService = empService;
		this.attendanceService = attendanceService;
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
}
