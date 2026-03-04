package com.company.edu.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.edu.entity.Category;
import com.company.edu.service.impl.CategoryService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/categories")
public class CategoryAdminController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/")
	public String list(@RequestParam(defaultValue = "1") int page, Model model) {
		int pageable=Math.max(page-1,0);
		Page<Category> PageCategory=categoryService.getAll(PageRequest.of(pageable, 10));
		long totalItems=PageCategory.getTotalElements();
		int totalPages=PageCategory.getTotalPages();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("categories", PageCategory);
		return "admin/category/list";
	}
	@GetMapping("/{i}")
	public String listPage(@PathVariable("i") int page, Model model) {
		int pageable=Math.max(page-1,0);
		Page<Category> PageCategory=categoryService.getAll(PageRequest.of(pageable, 10));
		long totalItems=PageCategory.getTotalElements();
		int totalPages=PageCategory.getTotalPages();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("categories", PageCategory);
		return "admin/category/list";
	}
	@GetMapping("/create")
	public String CreateCategory(Model model)
	{
		Category category=new Category();
		model.addAttribute("category", category);
		return "admin/category/create";
	}
	@PostMapping("/save")
	public String SaveCategory(@Valid @ModelAttribute("category") Category category,  BindingResult result,Model model)
	{   
		if (result.hasErrors()) {
	        return "admin/category/createError";
	    }
		categoryService.save(category);
		return "redirect:/admin/categories/";
	}
	@GetMapping("/edit/{id}")
	public String UpdateCategoty(@PathVariable("id") long id,Model model)
	{   
		Category category=categoryService.findById(id);
		model.addAttribute("category", category);
		return "admin/category/edit";
	}
	@GetMapping("/delete/{id}")
	public String DeleteCategoty(@PathVariable("id") long id,Model model)
	{   
		categoryService.deleteById(id);
		return "redirect:/admin/categories/";
	}
}
