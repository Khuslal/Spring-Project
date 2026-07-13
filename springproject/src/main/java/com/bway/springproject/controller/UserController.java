package com.bway.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bway.springproject.model.User;
import com.bway.springproject.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping({ "/", "/login" })
	public String getLogin() {
		return "LoginForm";
	}

	@PostMapping("/login")
	public String postLogin(@ModelAttribute User user, Model model, RedirectAttributes redirectAt,
			HttpSession session) {
		User dbuser = userService.userLogin(user.getUsername(), user.getPassword());

		if (dbuser != null) {
//			model.addAttribute("uname", dbuser.getFname());
			session.setAttribute("activeUser", dbuser);
			session.setMaxInactiveInterval(5 * 60);

			return "home";
		}
		redirectAt.addFlashAttribute("error", "Invalid username or password!");
		return "redirect:/login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "LoginForm";
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
	
	@GetMapping("/profile")
	public String getProfile(HttpSession session) {
		if(session.getAttribute("activeUser") == null) {
			return "LoginForm";
		}
		return "Profile";
	}
	
	
}
