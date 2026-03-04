package com.company.edu.controller.admin;

import java.time.LocalDateTime;

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
import com.company.edu.entity.Contact;
import com.company.edu.service.impl.CategoryService;
import com.company.edu.service.impl.ContactService;

@Controller
@RequestMapping("/admin")
public class ContactAminController {

	@Autowired
	private ContactService contactService;

	@GetMapping("/contacts")
	public String list(@RequestParam(defaultValue = "1") int page, Model model) {
		int pageable = Math.max(page - 1, 0);
		Page<Contact> PageContact = contactService.getAll(PageRequest.of(pageable, 10));
		long totalItems = PageContact.getTotalElements();
		int totalPages = PageContact.getTotalPages();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("contacts", PageContact);
		return "admin/contact/list";
	}

	@GetMapping("/contacts/{i}")
	public String listPage(@PathVariable("i") int page, Model model) {
		int pageable = Math.max(page - 1, 0);
		Page<Contact> PageContact = contactService.getAll(PageRequest.of(pageable, 10));
		long totalItems = PageContact.getTotalElements();
		int totalPages = PageContact.getTotalPages();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("contacts", PageContact);
		return "admin/contact/list";
	}
	@GetMapping("/contacts/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
	    Contact contact = contactService.findById(id);
	    model.addAttribute("contact", contact);
	    return "admin/contact/reply";
	}
	@PostMapping("/contacts/reply/{id}")
	public String reply(@PathVariable("id") Long id,
	                    @ModelAttribute Contact formContact) {

	    Contact contact = contactService.findById(id);

	    contact.setStatus(formContact.getStatus());
	    contact.setReplyContent(formContact.getReplyContent());
	    contact.setRepliedAt(LocalDateTime.now());

	    contactService.save(contact);

	    return "redirect:/admin/contacts";
	}
	@GetMapping("/contacts/delete/{id}")
	public String DeleteCongtact(@PathVariable("id") long id, Model model) {
		contactService.deleteById(id);
		return "redirect:/admin/contacts";
	}
}
