package com.nik.employeehub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.employeehub.model.LeaveRequest;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long>{

}
