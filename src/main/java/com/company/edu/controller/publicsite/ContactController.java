package com.company.edu.controller.publicsite;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.company.edu.entity.Contact;
import com.company.edu.repository.ContactRepository;
import com.company.edu.repository.ProgramRepository;

@Controller

public class ContactController {
	  @Autowired
	    private ContactRepository contactRepository;
	  @GetMapping("/lien-he")
	    public String showContactForm(Model model) {

	        model.addAttribute("contact", new Contact());

	        return "public/contact"; 
	    }
	  @PostMapping("/contact")
	    public String submitContact(@ModelAttribute Contact contact) {

	        contact.setCreatedAt(LocalDateTime.now());
	        contact.setStatus("NEW");

	        contactRepository.save(contact);

	        return "redirect:/lien-he?success=true";
	    }
}
