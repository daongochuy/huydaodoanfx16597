package com.company.edu.controller.publicsite;

import java.util.HashMap;
import java.util.List;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.edu.entity.Category;
import com.company.edu.entity.Program;
import com.company.edu.service.impl.ProgramImageService;
import com.company.edu.service.impl.homeService;

@Controller
@RequestMapping("/")
public class HomeController {
	  @Autowired
	    private homeService hService;
	  @Autowired
		private ProgramImageService programImageService;
	    @GetMapping("")
	    public String home() {

	        return "redirect:/home";
	    }
	    @GetMapping("home")
	    public String viewHome(Model model) {

	    	 // Lấy danh sách chương trình theo nhóm
	        Map<Category, List<Program>> groupedPrograms = hService.getHomeData();

	        // Tạo map chứa thumbnail
	        Map<Long, String> thumbnails = new HashMap<>();

	        // Lặp qua từng nhóm
	        for (List<Program> programs : groupedPrograms.values()) {

	            for (Program p : programs) {

	                String imageUrl = programImageService.getThumbnailUrl(p.getId());

	                thumbnails.put(
	                        p.getId(),
	                        imageUrl != null ? imageUrl : "/images/no-image.png"
	                );
	            }
	        }

	        model.addAttribute("groupedPrograms", groupedPrograms);
	        model.addAttribute("thumbnails", thumbnails);


	        return "public/home";
	    }
	   

}
