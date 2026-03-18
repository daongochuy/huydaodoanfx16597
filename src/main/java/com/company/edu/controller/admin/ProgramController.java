package com.company.edu.controller.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.company.edu.entity.Program;
import com.company.edu.service.impl.CategoryService;
import com.company.edu.service.impl.ExcelService;
import com.company.edu.service.impl.ProgramImageService;
import com.company.edu.service.impl.ProgramService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/programs")
public class ProgramController {
	@Autowired
	private ProgramService programService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProgramImageService programImageService;
	@Autowired
	private ExcelService excelService;

	@GetMapping("/")
	public String list(@RequestParam(defaultValue = "1") int page, Model model) {
		int pageable = Math.max(page - 1, 0);
		Page<Program> PageProgram = programService.getAll(PageRequest.of(pageable, 10));

		Map<Long, String> thumbnails = new HashMap<>();
		for (Program p : PageProgram) {
			thumbnails.put(p.getId(), programImageService.getThumbnailUrl(p.getId()));
		}

		long totalItems = PageProgram.getTotalElements();
		int totalPages = PageProgram.getTotalPages();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("programs", PageProgram);
		model.addAttribute("thumbnails", thumbnails);
		return "admin/program/list";
	}

	@GetMapping("/{i}")
	public String listPage(@PathVariable("i") int page, Model model) {
		int pageable = Math.max(page - 1, 0);
		Page<Program> PageProgram = programService.getAll(PageRequest.of(pageable, 10));
		Map<Long, String> thumbnails = new HashMap<>();
		for (Program p : PageProgram) {
			thumbnails.put(p.getId(), programImageService.getThumbnailUrl(p.getId()));
		}
		long totalItems = PageProgram.getTotalElements();
		int totalPages = PageProgram.getTotalPages();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("programs", PageProgram);
		model.addAttribute("thumbnails", thumbnails);
		return "admin/program/list";
	}

	/*
	 * @GetMapping("/create") public String CreateProgram(Model model) { Program
	 * program=new Program(); List<Category>
	 * listCategpory=categoryService.getActive(); model.addAttribute("categories",
	 * listCategpory); model.addAttribute("program", program); return
	 * "admin/program/create"; }
	 * 
	 * @PostMapping("/save") public String
	 * SaveProgram(@Valid @ModelAttribute("program") Program program,BindingResult
	 * result, Model model) { if (result.hasErrors()) {
	 * model.addAttribute("categories", categoryService.getActive());
	 * model.addAttribute("program", program); return "admin/program/createError"; }
	 * 
	 * programService.save(program); return "redirect:/admin/programs/"; }
	 */
	@GetMapping("/create")
	public String CreateProgram(Model model) {
		Program program = new Program();
		program.setCategory(new Category()); // rất quan trọng
		model.addAttribute("program", program);
		model.addAttribute("categories", categoryService.getActive());
		return "admin/program/create";
	}

	@PostMapping("/save")
	public String SaveProgram(@Valid @ModelAttribute("program") Program program, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("categories", categoryService.getActive());
			return "admin/program/create";
		}
		programService.save(program);
		return "redirect:/admin/programs/";
	}

	@GetMapping("/edit/{id}")
	public String UpdateProgram(@PathVariable("id") long id, Model model) {
		Program program = programService.findById(id);
		List<Category> listCategpory = categoryService.getActive();
		model.addAttribute("categories", listCategpory);
		model.addAttribute("program", program);
		return "admin/program/edit";
	}

	@PostMapping("search/")
	public String listSearch(@RequestParam(defaultValue = "1") int page, @RequestParam("keyword") String keyword,
			Model model) {
		int pageable = Math.max(page - 1, 0);
		Page<Program> PageProgram = programService.search(keyword, PageRequest.of(pageable, 10));
		Map<Long, String> thumbnails = new HashMap<>();
		for (Program p : PageProgram) {
			thumbnails.put(p.getId(), programImageService.getThumbnailUrl(p.getId()));
		}
		long totalItems = PageProgram.getTotalElements();
		int totalPages = PageProgram.getTotalPages();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("programs", PageProgram);
		model.addAttribute("thumbnails", thumbnails);
		return "admin/program/list";
	}

	@PostMapping("search/{i}")
	public String listSearchPage(@RequestParam(defaultValue = "1") int page, @RequestParam("keyword") String keyword,
			Model model) {
		int pageable = Math.max(page - 1, 0);
		Page<Program> PageProgram = programService.search(keyword, PageRequest.of(pageable, 10));
		long totalItems = PageProgram.getTotalElements();
		int totalPages = PageProgram.getTotalPages();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("programs", PageProgram);
		return "admin/program/list";
	}

	@GetMapping("/delete/{Id}")
	public String deleleProgram(@PathVariable("Id") long Id) {
		this.programService.DeleteById(Id);
		return "redirect:/admin/programs/";
	}

	@GetMapping("/deleteAll")
	public String deleteProgramByAll() {
		this.programService.DeleteAll();
		return "redirect:/admin/programs/";
	}

	@PostMapping("/delete")
	public String mutildelete(@RequestParam("lid") long[] lid) {
		if (lid.length == 0) {
			return "redirect:/admin/programs/";
		} else {
			this.programService.DeleteByList(lid);
			return "redirect:/admin/programs/";
		}

	}

	@GetMapping("/export/programs")
	public void exportUsers(HttpServletResponse response) throws IOException {

		List<Program> Programs = programService.GetList();

		String[] headers = { "ID", "Title", "Country" };
		String[] fields = { "id", "title", "country" };

		excelService.exportToExcel(Programs, headers, fields, "Programs", response);
	}

}
