package com.company.edu.controller.admin;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.edu.entity.User;
import com.company.edu.service.impl.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private UserService userService;
	@GetMapping("/signin")
	public String signin() {
	    return "admin/user/login";
	}
	@ModelAttribute
	private void userDetails(Model m, Principal p) {
		if (p != null) {
			String email = p.getName();
			User user = userService.findByEmail(email);

			m.addAttribute("user", user);
		}
	}
	@GetMapping("")
	private String HomeView() {
		return "admin/user/login";
	}
	
	@GetMapping("/user/signin")
	private String HomeLogin() {
		
		return "admin/user/login";
	}
	

}
