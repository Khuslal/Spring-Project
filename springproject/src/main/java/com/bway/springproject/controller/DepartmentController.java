package com.bway.springproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bway.springproject.model.Department;
import com.bway.springproject.service.DepartmentService;

@Controller
public class DepartmentController {
	private final DepartmentService deptService;

	DepartmentController(DepartmentService deptService) {
		this.deptService = deptService;
	}
	
	@GetMapping("/department")
	public String getDepartment() {
		return "DepartmentForm";
	}
	
	@PostMapping("/department")
	public String postDepartment(@ModelAttribute Department dept, Model model) {
		deptService.addDept(dept);
		model.addAttribute("message", "Data Save Successfully.");
		return "DepartmentForm";
	}
	
	@GetMapping("/departmentList")
	public String getAllDepartment(Model model) {
		model.addAttribute("dList", deptService.getAllDepts());
		return "DepartmentListForm";
	}
	
	@GetMapping("/department/edit") 
	// @RequestParam("id") is the thymeleaf variable name
	public String edit(@RequestParam("id") int deptId, Model model) {
		model.addAttribute("dModel", deptService.getDeptByID(deptId));
		return "DepartmentEditForm";
	}
	
	@PostMapping("/department/update")
	public String update(@ModelAttribute Department dept) {
		deptService.updateDept(dept);
		return "redirect:/departmentList";
	}
	
	@GetMapping("/department/delete")
	public String delete(@RequestParam("id") int del) {
		deptService.deleteDept(del);
		return "redirect:/departmentList";
	}
}
