package com.company.edu.controller.publicsite;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.edu.entity.Member;
import com.company.edu.entity.Program;
import com.company.edu.entity.Registration;
import com.company.edu.service.impl.MemberService;
import com.company.edu.service.impl.ProgramImageService;
import com.company.edu.service.impl.ProgramService;
import com.company.edu.service.impl.RegistrationService;

@Controller
@RequestMapping("/programs")
public class ProgramPageController {
	
	  @Autowired
	    private ProgramService programService;
	  @Autowired
		private ProgramImageService programImageService;
	  @Autowired
	    private MemberService memberService;

	    @Autowired
	    private RegistrationService registrationService;
	    @GetMapping("/{id}")
	    public String showDetail(@PathVariable("id") Long id, Model model) {

	    	  Program program = programService.findById(id);

	    	    if (program == null) {
	    	        return "error/404";
	    	    }

	    	    // Tạo map thumbnail giống trang list
	    	    Map<Long, String> thumbnails = new HashMap<>();

	    	    String imageUrl = programImageService.getThumbnailUrl(program.getId());

	    	    thumbnails.put(
	    	            program.getId(),
	    	            imageUrl != null
	    	                    ?  imageUrl
	    	                    : "/images/no-image.png"
	    	    );
	    	    model.addAttribute("program", program);
	    	    model.addAttribute("thumbnails", thumbnails);

	        return "public/programs/detail";
	    }

		@GetMapping("/{id}/register")
		public String showRegisterForm(@PathVariable("id") Long programId, Model model) {

			Program program = programService.findById(programId);
			if (program == null) {
				return "redirect:/";
			}

			model.addAttribute("program", program);
			model.addAttribute("member", new Member());

			return "public/programs/register";
		}

		// ==========================
		// 2️⃣ POST - Xử lý đăng ký
		// ==========================
		@PostMapping("/{id}/register")
		public String registerProgram(@PathVariable("id") Long programId, @ModelAttribute Member member,
				@RequestParam(required = false) String note) {

			Program program = programService.findById(programId);
			if (program == null) {
				return "redirect:/";
			}

			// 1️⃣ Lưu Member
			member.setCreatedAt(LocalDateTime.now());
			member.setStatus("ACTIVE");
			member.setPassword("123456");
			Member savedMember = memberService.save(member);

			// 2️⃣ Lưu Registration
			Registration registration = new Registration();
			registration.setMember(savedMember);
			registration.setProgram(program);
			registration.setStatus("PENDING");
			registration.setNote(note);
			registration.setCreatedAt(LocalDateTime.now());

			registrationService.save(registration);

			return "redirect:/programs/" + programId + "?success=true";
		}
}
