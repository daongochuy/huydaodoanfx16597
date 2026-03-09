package com.company.edu.controller.publicsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.company.edu.entity.Post;
import com.company.edu.service.impl.PostService;
import com.company.edu.service.impl.homeService;

@Controller
public class introController {
	  @Autowired
	    private PostService postService;
	@GetMapping("/gioi-thieu")
	public String intro(Model model) {
        Post post=postService.getPostByCategory("gioi_thieu");
        model.addAttribute("intro", post);
		return "public/intro";
	}

}
