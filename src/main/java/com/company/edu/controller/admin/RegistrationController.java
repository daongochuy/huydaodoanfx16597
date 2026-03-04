package com.company.edu.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.edu.entity.Program;
import com.company.edu.entity.Registration;
import com.company.edu.service.impl.RegistrationService;

@Controller
@RequestMapping("/admin/registration")
public class RegistrationController {
	@Autowired
	private RegistrationService registrationService;

	@GetMapping({ "", "/" })
	public String listRegistion(@RequestParam(defaultValue = "1") int page,
			@RequestParam(required = false) String status, Model model) {
		int pageable = Math.max(page - 1, 0);
		Page<Registration> PageRegistration;
		if (status != null && !status.isEmpty()) {
			PageRegistration = registrationService
	                .findByStatus(status, PageRequest.of(pageable, 10));
	    } else {
	    	PageRegistration = registrationService
	                .GetAll(PageRequest.of(pageable, 10));
	    }
		long totalItems = PageRegistration.getTotalElements();
		int totalPages = PageRegistration.getTotalPages();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("registrations", PageRegistration);
		model.addAttribute("status", status);
		return "admin/registration/list";
	}

	/*
	 * @GetMapping({ "/{i}", "/{i}/" }) public String
	 * listRegistionPage(@PathVariable("i") int page,@RequestParam(required = false)
	 * String status, Model model) { int pageable = Math.max(page - 1, 0);
	 * Page<Registration> PageRegistration; if (status != null && !status.isEmpty())
	 * { PageRegistration = registrationService .findByStatus(status,
	 * PageRequest.of(pageable, 10)); } else { PageRegistration =
	 * registrationService .GetAll(PageRequest.of(pageable, 10)); } long totalItems
	 * = PageRegistration.getTotalElements(); int totalPages =
	 * PageRegistration.getTotalPages(); model.addAttribute("currentPage", page);
	 * model.addAttribute("totalPages", totalPages);
	 * model.addAttribute("totalItems", totalItems);
	 * model.addAttribute("registrations", PageRegistration);
	 * model.addAttribute("status", status); return "admin/registration/list"; }
	 */
	@GetMapping("/detail/{id}")
	public String viewDetail(@PathVariable Long id, Model model) {

	    Registration registration = registrationService.findById(id);

	    model.addAttribute("registration", registration);

	    return "admin/registration/detail";
	}
	@PostMapping("/update-status")
	public String updateStatus(@RequestParam Long id,
	                           @RequestParam String status,
	                           @RequestParam(required = false) String note) {

		
	    registrationService.updateStatus(id, status, note);

	    return "redirect:/admin/registration/detail/" + id+"?success=true";
	}
	
	@GetMapping("/approve/{id}")
	public String approveRegistration(@PathVariable Long id,
	                                  @RequestParam(defaultValue = "1") int page,
	                                  @RequestParam(required = false) String status) {

	    registrationService.approve(id);

	    return "redirect:/admin/registration?page=" + page +
	           (status != null ? "&status=" + status : "");
	}
}
