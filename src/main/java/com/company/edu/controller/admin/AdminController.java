package com.company.edu.controller.admin;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.edu.entity.Category;
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
	@GetMapping("/403")
	private String Show403() {

		return "admin/user/403";
	}

	@GetMapping("/admin/user")
	public String list(@RequestParam(defaultValue = "1") int page, Model model) {
		int pageable=Math.max(page-1,0);
		Page<User> PageUser=userService.getAll(PageRequest.of(pageable, 10));
		long totalItems=PageUser.getTotalElements();
		int totalPages=PageUser.getTotalPages();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("users", PageUser);
		return "admin//admin/user/list";
	}

	@GetMapping("/admin/user/{i}")
	public String listPage(@PathVariable("i") int page, Model model) {
		int pageable = Math.max(page - 1, 0);
		Page<User> PageUser = userService.getAll(PageRequest.of(pageable, 10));
		long totalItems = PageUser.getTotalElements();
		int totalPages = PageUser.getTotalPages();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("users", PageUser);
		return "admin/user/list";
	}

	@GetMapping("/admin/user/delete/{id}")
	public String DeleteUser(@PathVariable("id") long id, Model model) {
		userService.deleteById(id);
		return "redirect:/admin/admin/user";
	}

}
