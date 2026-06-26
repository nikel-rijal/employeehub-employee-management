package com.nik.employeehub.service;

import com.nik.employeehub.model.Department;

public interface DepartmentService {

	void addDepartment(Department department);
	void deleteDepartment(Long id);
	void updateDepartment(Department department);
}
