package com.company.edu.controller.admin;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.edu.entity.User;
import com.company.edu.repository.UserRepository;
import com.company.edu.service.impl.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "admin/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncode;

	private void userDetails(Model m, Principal p) {
		String email = p.getName();
		User user = userService.findByEmail(email);

		m.addAttribute("user", user);

	}

	@GetMapping("/create")
	private String UserCreate(Model model) {
		User myUser = new User();
		model.addAttribute("myUser", myUser);
		return "admin/user/createUser";
	}

	@PostMapping("/create")
	private String saveUser(@ModelAttribute("myUser") User myUser, Model model, HttpSession session) {
		boolean f = userService.checkEmail(myUser.getEmail());
		Object msg = session.getAttribute("msg");

		if (f) {
			session.setAttribute("msg", "Email Id alreday exists");
		}

		else {
			User uSer = userService.createUser(myUser);
			if (uSer != null) {
				session.setAttribute("msg", "Register Sucessfully");
			} else {
				session.setAttribute("msg", "Something wrong on server");
			}
		}

		return "redirect:/admin/user/create";

	}

	@GetMapping("/register")
	private String HomeRegister(HttpSession session, Model model) {
		Object msg = session.getAttribute("msg");
		if (msg != null) {
			model.addAttribute("msg", msg);
			session.removeAttribute("msg"); // xoá msg sau khi hiển thị
		}

		return "admin/user/register";
	}

	@PostMapping("/createUser")
	public String createuser(@ModelAttribute User user, HttpSession session) {

		// System.out.println(user);

		boolean f = userService.checkEmail(user.getEmail());
		Object msg = session.getAttribute("msg");

		if (f) {
			session.setAttribute("msg", "Email Id alreday exists");
		}

		else {
			User uSer = userService.createUser(user);
			if (uSer != null) {
				session.setAttribute("msg", "Register Sucessfully");
			} else {
				session.setAttribute("msg", "Something wrong on server");
			}
		}

		return "redirect:/admin/user/register";
	}

	@GetMapping("/changePassword")
	public String loadChangePassword() {
		return "admin/user/change_password";
	}

	@PostMapping("/changePassword")

	public String changePassword(Principal p, @RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword, HttpSession session) {
		String email = p.getName();
		User loginuser = userService.findByEmail(email);
		boolean ck = passwordEncode.matches(oldPassword, loginuser.getPassword());
		if (ck) {
			loginuser.setPassword(newPassword);
			User updateUser = userService.saveUser(loginuser);
			if (updateUser != null) {
				session.setAttribute("msg", "change password successsfully!");

			}
		} else {
			session.setAttribute("msg", "Wrong old password ");
		}

		return "redirect:/changePassword";
	}

	@GetMapping("/forgot-password")
	public String forgotPass() {
		return "admin/user/forgot_password";
	}

	@GetMapping("/reset-password/{id}")
	public String showResetPassword(@PathVariable("id") long id, Model model) {
	    model.addAttribute("id", id);
	    return "admin/user/reset_password"; 
	}

	@PostMapping("/forgot-password")
	public String forgotPassword(@RequestParam("email") String email, @RequestParam("mobileNum") String mobileNum,
			HttpSession session, Model model) {

		User user = userService.findByEmailAndMobileNumber(email, mobileNum);

		if (user != null) {
			return "redirect:/admin/user/reset-password/"+user.getId();

		} else {
			session.setAttribute("msg", "Email hoặc số điện thoại không đúng");
			return "redirect:/admin/user/forgot-password";
		}

	}

	@PostMapping("/reset-password/{id}")
	public String resetPassword(@PathVariable("id") long id,
	                            @RequestParam("pw") String pw,
	                            @RequestParam("cpw") String cpw,
	                            HttpSession session, Model model) {
		 model.addAttribute("id", id);
	    if (!pw.equals(cpw)) {
	        session.setAttribute("msg", "Mật khẩu nhập lại không khớp");
	        return "redirect:/admin/user/reset-password/" + id;
	    }

	    User user = userService.findById(id);
	    System.out.print(user.getId());
	    if (user != null) {
	        user.setPassword(pw);
	        userService.saveUser(user);
	        session.setAttribute("msg", "Đổi mật khẩu thành công");
	        return "redirect:/admin/user/forgot-password";
	    } else {
	        session.setAttribute("msg", "User không tồn tại");
	        return "redirect:/admin/user/reset-password/" + id;
	    }
	}

}
