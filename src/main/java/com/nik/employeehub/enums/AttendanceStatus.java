package com.nik.employeehub.enums;

public enum AttendanceStatus {

	PRESENT("Present"),
	ABSENT("Absent"),
	ON_LEAVE("on_leave");
	
	private final String displayName;
	
	private AttendanceStatus(String displayName) {
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return displayName;
	}
}
