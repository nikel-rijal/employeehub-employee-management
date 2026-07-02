package com.nik.employeehub.dto;

import com.nik.employeehub.enums.EmployeeRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeUpdateDTO {

	private Long id;
	
	@NotBlank(message = "Firstname is required!!")
	@Size(min = 2, max = 30, message = "Firstname must be between 2 to 30 characters")
	@Pattern(regexp = "^[A-Za-z ]+$", message = "Firstname can contain only letters")
	private String firstname;
	
	@NotBlank(message = "Lastname is required!!")
	@Size(min = 2, max = 30, message = "Lastname must be between 2 to 30 characters")
	@Pattern(regexp = "^[A-Za-z ]+$", message = "Lastname can contain only letters")
	private String lastname;
	
	@NotBlank(message = "Email is required!!")
	@Email(message = "Invalid email format")
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Invalid email format")
	private String email;
	
	@NotBlank(message = "Address is required!!")
	@Pattern(regexp = "^[A-Za-z0-9\\s,./#-]+$", message = "Address contain invalid characters")
	private String address;
	
	@NotBlank(message = "Phone Number is required!!")
	@Pattern(regexp = "^[0-9]+$", message = "Phone number only contain digits")
	private String phoneNumber;
	
	@NotNull(message = "Role is required!!")
	private EmployeeRole role;
}
