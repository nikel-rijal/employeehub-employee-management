package com.nik.employeehub.serviceImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nik.employeehub.enums.AttendanceStatus;
import com.nik.employeehub.model.Attendance;
import com.nik.employeehub.model.Employee;
import com.nik.employeehub.repository.AttendanceRepository;
import com.nik.employeehub.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService {
	
	private final AttendanceRepository attendanceRepo;
	
	public AttendanceServiceImpl(AttendanceRepository attendanceRepo) {
		this.attendanceRepo = attendanceRepo;
	}

	@Override
	public void addAttendance(Attendance attendance) {
		
		attendanceRepo.save(attendance);
		
	}

	@Override
	public void deleteAttendance(Long id) {
		
		attendanceRepo.deleteById(id);
		
	}

	@Override
	public void updateAttendance(Attendance attendance) {
		
		attendanceRepo.save(attendance);
		
	}
	
	@Override
	public Attendance findAttendanceById(Long id) {
		
		return attendanceRepo.findById(id).orElseThrow();
	}

	@Override
	public Attendance attendanceRecord(Employee employee, LocalDate date) {
		
		return attendanceRepo.findByEmployeeAndDate(employee, date).orElse(null);
		
	}

	@Override
	public void entryAttendance(Employee employee) {
		
		Attendance attendance = new Attendance();
		
		attendance.setEmployee(employee);
		attendance.setDate(LocalDate.now());
		attendance.setEntryTime(LocalTime.now());
		attendance.setStatus(AttendanceStatus.PRESENT);
		
		attendanceRepo.save(attendance);
	}

	@Override
	public void exitAttendance(Employee employee) {

		Attendance attendance = attendanceRepo.findByEmployeeAndDate(employee, LocalDate.now()).orElseThrow(() -> new RuntimeException("Attendance not found!!"));
		
		attendance.setLeaveTime(LocalTime.now());
		attendance.setHours(ChronoUnit.HOURS.between(attendance.getEntryTime(), attendance.getLeaveTime()));
		
		attendanceRepo.save(attendance);
	}

	@Override
	public List<Attendance> findAttendanceByEmployee(Employee employee) {
		
		return attendanceRepo.findByEmployee(employee);
	}

	

	

}
