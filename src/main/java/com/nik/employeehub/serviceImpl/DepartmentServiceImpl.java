package com.nik.employeehub.serviceImpl;

import org.springframework.stereotype.Service;

import com.nik.employeehub.model.Department;
import com.nik.employeehub.repository.DepartmentRepository;
import com.nik.employeehub.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	private final DepartmentRepository depRepo;
	
	public DepartmentServiceImpl(DepartmentRepository depRepo) {
		this.depRepo = depRepo;
	}

	@Override
	public void addDepartment(Department department) {
		
		depRepo.save(department);
		
	}

	@Override
	public void deleteDepartment(Long id) {
		
		depRepo.deleteById(id);
		
	}

	@Override
	public void updateDepartment(Department department) {
		
		depRepo.save(department);
		
	}

}
