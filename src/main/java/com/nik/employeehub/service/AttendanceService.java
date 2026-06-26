package com.nik.employeehub.service;

import java.time.LocalDate;
import java.util.List;

import com.nik.employeehub.model.Attendance;
import com.nik.employeehub.model.Employee;

public interface AttendanceService {

	void addAttendance(Attendance attendance);
	void deleteAttendance(Long id);
	void updateAttendance(Attendance attendance);
	Attendance findAttendanceById(Long id);
	Attendance attendanceRecord(Employee employee, LocalDate date);
	void entryAttendance(Employee employee);
	void exitAttendance(Employee employee);
	List<Attendance> findAttendanceByEmployee(Employee employee);
}
