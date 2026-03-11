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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.edu.entity.Category;
import com.company.edu.entity.User;
import com.company.edu.service.impl.UserService;

import jakarta.servlet.http.HttpSession;

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
	public String list(@RequestParam(defaultValue = "1") int page, @RequestParam(required = false) String keyword,
			Model model) {

		int pageable = Math.max(page - 1, 0);

		Page<User> pageUser;

		if (keyword != null && !keyword.trim().isEmpty()) {
			pageUser = userService.findByEmailorPhone(keyword, PageRequest.of(pageable, 10));
		} else {
			pageUser = userService.getAll(PageRequest.of(pageable, 10));
		}

		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", pageUser.getTotalPages());
		model.addAttribute("totalItems", pageUser.getTotalElements());
		model.addAttribute("users", pageUser);
		model.addAttribute("keyword", keyword);

		return "admin/user/list";
	}

	@GetMapping("/admin/user/{i}")
	public String listPage(
	        @PathVariable("i") int page,
	        @RequestParam(required = false) String keyword,
	        Model model) {

	    int pageable = Math.max(page - 1, 0);

	    Page<User> pageUser;

	    if (keyword != null && !keyword.trim().isEmpty()) {
	        pageUser = userService.findByEmailorPhone(keyword, PageRequest.of(pageable, 10));
	    } else {
	        pageUser = userService.getAll(PageRequest.of(pageable, 10));
	    }

	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", pageUser.getTotalPages());
	    model.addAttribute("totalItems", pageUser.getTotalElements());
	    model.addAttribute("users", pageUser);
	    model.addAttribute("keyword", keyword);

	    return "admin/user/list";
	}

	@GetMapping("/admin/user/delete/{id}")
	public String DeleteUser(@PathVariable("id") long id, Model model) {
		userService.deleteById(id);
		return "redirect:/admin/admin/user";
	}
	@GetMapping("/admin/user/create")
	private String UserCreate(Model model) {
		User myUser = new User();
		model.addAttribute("myUser", myUser);
		return "admin/user/createUser";
	}

	@PostMapping("/admin/user/create")
	private String saveUser(@ModelAttribute("myUser") User myUser, Model model, HttpSession session) {
		boolean f = userService.checkEmail(myUser.getEmail());
		Object msg = session.getAttribute("msg");

		if (f) {
			session.setAttribute("msg", "Email Id alreday exists");
		}

		else {
			User uSer = userService.createUser(myUser);
			if (uSer != null) {
				session.setAttribute("msg", "Create Sucessfully");
			} else {
				session.setAttribute("msg", "Something wrong on server");
			}
		}
		
		return "redirect:/admin/admin/user";

	}
	@GetMapping("/admin/user/edit/{id}")
	private String UserEdit(@PathVariable("id") long id,Model model) {
		User myUser = userService.findById(id);
		model.addAttribute("myUser", myUser);
		return "admin/user/edit";
	}
	@PostMapping("/admin/user/save")
	private String saveEditUser(@ModelAttribute("myUser") User myUser, Model model, HttpSession session) {
		   Object msg = session.getAttribute("msg");
		   User oldUser=userService.findById(myUser.getId());
		   if(myUser.getFullName() != null){
		        oldUser.setFullName(myUser.getFullName());
		    }

		    if(myUser.getEmail() != null){
		        oldUser.setEmail(myUser.getEmail());
		    }

		    if(myUser.getUsername() != null){
		        oldUser.setUsername(myUser.getUsername());
		    }

		    if(myUser.getMobileNumber() != null){
		        oldUser.setMobileNumber(myUser.getMobileNumber());
		    }

		    if(myUser.getRole() != null){
		        oldUser.setRole(myUser.getRole());
		    }

		    if(myUser.getPassword() != null && !myUser.getPassword().isEmpty()){
		        oldUser.setPassword(myUser.getPassword());
		    }

			User uSer = userService.saveUser(oldUser);
			if (uSer != null) {
				session.setAttribute("msg", "Create Sucessfully");
			} else {
				session.setAttribute("msg", "Something wrong on server");
			}
		return "redirect:/admin/admin/user";

	}
}
