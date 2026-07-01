package com.nik.employeehub.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LeaveRequestDTO {

	@NotNull(message = "Date is required!!")
	@FutureOrPresent(message = "Date must be in present or future!!")
	private LocalDate fromDate;
	
	@NotNull(message = "Date is required!!")
	@FutureOrPresent(message = "Date must be in present or future!!")
	private LocalDate toDate;
	
	@NotBlank(message = "Reason is required!!")
	private String reason;
}
