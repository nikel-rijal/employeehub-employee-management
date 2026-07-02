package com.nik.employeehub.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nik.employeehub.dto.EmployeeDTO;
import com.nik.employeehub.dto.EmployeeUpdateDTO;
import com.nik.employeehub.enums.EmployeeRole;
import com.nik.employeehub.model.Employee;
import com.nik.employeehub.service.EmployeeService;

import jakarta.validation.Valid;

@Controller
public class AdminController {
	
	private final EmployeeService empService;
	
	public AdminController(EmployeeService empService) {
		
		this.empService = empService;
	}

	@GetMapping("/admin/dashboard")
	public String getDashboard() {
		
		return "AdminDashboard";
	}
	
	@GetMapping("/admin/employee")
	public String getManageEmployees() {
		
		return "AdminEmployeeDashboard";
	}
	
	@GetMapping("/admin/employee/add")
	public String getAddEmployee(Model model) {
		
		model.addAttribute("employee", new Employee());
		model.addAttribute("roles", EmployeeRole.values());
		return "AdminAddEmployee";
	}
	
	@PostMapping("/admin/employee/add")
	public String postAddEmployee(@Valid @ModelAttribute("employee") EmployeeDTO dto, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
		
		if(result.hasErrors()) {
			
			model.addAttribute("roles", EmployeeRole.values());
			return "AdminAddEmployee";
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
			employee.setRole(dto.getRole());
			
			empService.addEmployee(employee);
			
			redirectAttributes.addFlashAttribute("success", "Employee added successfully!!");
			return "redirect:/admin/employee/list";
		}
		
		model.addAttribute("error", "Email already exist!!");
		return "AdminAddEmployee";
	}
	
	@GetMapping("/admin/employee/list")
	public String getEmployeeList(Model model) {
		
		List<Employee> elist = empService.findEmployees();
		
		model.addAttribute("elist", elist);
		return "AdminEmployeeList";
	}
	
	@GetMapping("/admin/employee/list/view/{id}")
	public String getViewEmployee(@PathVariable Long id, Model model) {
		
		Employee employee = empService.findByEmployeeId(id);
		
		model.addAttribute("employee", employee);
		return "AdminViewEmployee";
	}
	
	@GetMapping("/admin/employee/list/edit/{id}")
	public String getEditEmployee(@PathVariable Long id, Model model) {
		
		Employee employee = empService.findByEmployeeId(id);
		
		model.addAttribute("employee", employee);
		model.addAttribute("roles", EmployeeRole.values());
		return "AdminEditEmployee";
	}
	
	@PostMapping("/admin/employee/list/edit")
	public String postUpdateEmployee(@Valid @ModelAttribute("employee") EmployeeUpdateDTO dto, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
		
		if(result.hasErrors()) {
			
			model.addAttribute("roles", EmployeeRole.values());
			return "AdminEditEmployee";
		}
		
		boolean isEmailExist = empService.findEmailExistForOtherEmployee(dto.getEmail(), dto.getId());
		
		if(isEmailExist) {
			
			model.addAttribute("error", "Email already exist for another employee!!");
			model.addAttribute("roles", EmployeeRole.values());
			return "AdminEditEmployee";
		}
		
		Employee employee = empService.findByEmployeeId(dto.getId());
		
		employee.setFirstname(dto.getFirstname());
		employee.setLastname(dto.getLastname());
		employee.setEmail(dto.getEmail());
		employee.setAddress(dto.getAddress());
		employee.setPhoneNumber(dto.getPhoneNumber());
		employee.setRole(dto.getRole());
		
		empService.updateEmployee(employee);
		
		redirectAttributes.addFlashAttribute("success", "Employee updated successfully!!");
		return "redirect:/admin/employee/list";
	}
	
	@GetMapping("/admin/employee/list/delete/{id}")
	public String deleteEmployee(@PathVariable Long id,RedirectAttributes redirectAttributes) {
		
		empService.deleteEmployee(id);
		
		redirectAttributes.addFlashAttribute("success", "Employee deleted Successfully!!");
		return "redirect:/admin/employee/list";
	}
}
