package com.bway.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bway.springproject.model.User;
import com.bway.springproject.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping({ "/", "/login" })
	public String getLogin() {
		return "LoginForm";
	}

	@PostMapping("/login")
	public String postLogin(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
		User dbuser = userService.userLogin(user.getUsername(), user.getPassword());

		if (dbuser != null) {
			
			return "redirect:/home";
		}
		redirectAttributes.addFlashAttribute("error", "Invalid username or password!");
		return "redirect:/login";
	}

	@GetMapping("/home")
	public String getHOme() {
		return "Home";
	}
	
	@GetMapping("/signup")
	public String getSignup() {
		return "SignupForm";
	}

	@PostMapping("/signup")
	public String postSignup(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
		userService.userSignup(user);
		
		redirectAttributes.addFlashAttribute("message", "Data saved successfully.");
		return "redirect:/login";
	}
}
