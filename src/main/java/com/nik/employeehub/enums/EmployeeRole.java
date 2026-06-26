package com.nik.employeehub.enums;

public enum EmployeeRole {

	EMPLOYEE("Employee"),
	ADMIN("Admin"),
	MANAGER("Manager");
	
	private final String displayName;
	
	private EmployeeRole(String displayName) {
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return displayName;
	}
}
