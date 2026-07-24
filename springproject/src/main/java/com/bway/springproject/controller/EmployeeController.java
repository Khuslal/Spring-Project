package com.bway.springproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bway.springproject.model.Employee;
import com.bway.springproject.service.DepartmentService;
import com.bway.springproject.service.EmployeeService;

@Controller
public class EmployeeController {
	
	private final EmployeeService empService;
	
	private final DepartmentService deptService;

	EmployeeController(EmployeeService empService, DepartmentService deptService) {
		this.empService = empService;
		this.deptService = deptService;
	}
	
	@GetMapping({"/employee/add","/employee/add/"})
	public String getEmployee(Model model) {
		model.addAttribute("dList", deptService.getAllDepts());
		return "EmployeeForm";
	}
	
	@PostMapping({"/employee/add","/employee/add/"})
	public String postEmployee(@ModelAttribute Employee emp) {
		empService.addEmp(emp);
		return "redirect:/employee";
	}
	
	@GetMapping({"/employee","/employee/"})
	public String getAllEmployee(Model model) {
		model.addAttribute("eList", empService.getAllEmps());
		return "EmployeeListForm";
	}
	
	@GetMapping("/employee/edit") 
	// @RequestParam("id") is the thymeleaf variable name
	public String edit(@RequestParam("id") int empId, Model model) {
		model.addAttribute("eModel", empService.getEmpById(empId));
		return "EmployeeEditForm";
	}
	
	@PostMapping("/employee/update")
	public String update(@ModelAttribute Employee emp) {
		empService.updateEmp(emp);
		return "redirect:/employee";
	}
	
	@GetMapping({"/employee/delete","/employee/delete/"})
	public String delete(@RequestParam("id") int del) {
		empService.deleteEmp(del);
		return "redirect:/employee";
	}
}
