package com.company.edu.controller.publicsite;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.edu.repository.MemberRepository;
import com.company.edu.repository.ProgramRepository;
import com.company.edu.service.impl.ProgramImageService;
import com.company.edu.entity.Program;
@Controller
@RequestMapping("/categories")
public class CategoryController {
	 @Autowired
	    private ProgramRepository programService;
	  @Autowired
		private ProgramImageService programImageService;
	  

	@GetMapping({"/{id}"})
	public String viewByCategory(@PathVariable Long id,
	                             @RequestParam(defaultValue = "1") int page,
	                             Model model) {
       int curPage=Math.max(page-1, 0);
	    Page<Program> programPage = programService
	            .findByCategoryId(id, PageRequest.of(curPage, 10));

	    Map<Long, String> thumbnails = new HashMap<>();
		for (Program p : programPage) {
			thumbnails.put(p.getId(), programImageService.getThumbnailUrl(p.getId()));
		}

		long totalItems = programPage.getTotalElements();
		int totalPages = programPage.getTotalPages();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("CategoryId", id);
		model.addAttribute("programs", programPage.getContent());
		model.addAttribute("thumbnails", thumbnails);

	    return "public/categories/list";
	}
}
