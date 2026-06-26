package com.nik.employeehub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.employeehub.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
