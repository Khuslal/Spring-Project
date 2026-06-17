package com.bway.springproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	// {"/", "/login"} --> creates multiple location for the same page
	@GetMapping({"/", "/login"})
	public String getLogin() {
		return "LoginForm";
	}
	
	@GetMapping("/signup")
	public String getSignup() {
		return "SignupForm";
	}
}
