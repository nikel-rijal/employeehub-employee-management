package com.nik.employeehub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nik.employeehub.dto.EmployeeDTO;
import com.nik.employeehub.model.Employee;
import com.nik.employeehub.service.EmployeeService;

import jakarta.validation.Valid;

@Controller
public class AuthController {
	
	private final EmployeeService empService;
	
	public AuthController(EmployeeService empService) {
		this.empService = empService;
	}

	@GetMapping("/login")
	public String getLogin() {
		
		return "LoginForm";
	}
	
	@GetMapping("/signup")
	public String getSignUp(Model model) {
		
		model.addAttribute("employee", new Employee());
		return "SignUpForm";
	}
	
	@PostMapping("/signup")
	public String postSignUp(@Valid @ModelAttribute("employee") EmployeeDTO dto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			return "SignUpForm";
		}
		
		Employee emp = empService.findByEmail(dto.getEmail());
		if(emp == null) {
			
			Employee employee = new Employee();
			
			employee.setFirstname(dto.getFirstname());
			employee.setLastname(dto.getLastname());
			employee.setEmail(dto.getEmail());
			employee.setPassword(dto.getPassword());
			employee.setAddress(dto.getAddress());
			employee.setPhoneNumber(dto.getPhoneNumber());
			employee.setJoinedDate(dto.getJoinedDate());
			
			empService.addEmployee(employee);
			redirectAttributes.addFlashAttribute("success", "Registration Successfull!!");
			
			return "redirect:/login";
		}
		
		model.addAttribute("error", "Email already exist!!");
		return "SignUpForm";
	}
}
