package com.company.edu.controller.publicsite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.company.edu.entity.Category;
import com.company.edu.service.impl.CategoryService;

import java.util.List;
@ControllerAdvice(basePackages = "com.company.edu.controller.publicsite")
public class PublicModelAdvice {
	   @Autowired
		private CategoryService categoryService;

	    
	    @ModelAttribute("categories")
	    public List<Category> loadCategories() {
	        return categoryService.findByType("xuat_khau_lao_dong");
	    }
	    @ModelAttribute("categorystudys")
	    public List<Category> loadCategorystudy() {
	        return categoryService.findByType("du_hoc");
	    }
	    @ModelAttribute("categoryengs")
	    public List<Category> loadCategoryengs() {
	        return categoryService.findByType("ky_su");
	    }
	    @ModelAttribute("categorytrainings")
	    public List<Category> loadCategorytrainings() {
	        return categoryService.findByType("khoa_hoc");
	    }
}
