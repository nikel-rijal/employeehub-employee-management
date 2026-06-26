package com.nik.employeehub.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeDTO {
	
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
	
	@NotBlank(message = "Password is required!!")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$", message = "Password must contain atleast 8 characters, one uppercase letter, one lowercase letter and one number")
	private String password;
	
	@NotBlank(message = "Address is required!!")
	@Pattern(regexp = "^[A-Za-z0-9\\s,./#-]+$", message = "Address contain invalid characters")
	private String address;
	
	@NotBlank(message = "Phone number is required!!")
	@Pattern(regexp = "^[0-9]+$", message = "Phone number only contain digits")
	private String phoneNumber;
	
	@NotNull(message = "Date is required!!")
	@PastOrPresent(message = "Date must be in past or present")
	private LocalDate joinedDate;
}
