package com.company.edu.controller.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.company.edu.entity.Category;
import com.company.edu.entity.ProgramImage;
import com.company.edu.service.impl.ProgramImageService;
import com.company.edu.service.impl.ProgramService;

@Controller
@RequestMapping("/admin/program-images")
public class ProgramImageController {
	@Autowired
	private ProgramImageService programImageService;
	@Autowired
	private ProgramService programService;
	@Value("${app.upload-dir}")
	private String uploadDir;
	@GetMapping("/list")
	public String list(@RequestParam(defaultValue = "1") int page, Model model) {
		int pageable=Math.max(page-1,0);
		Page<ProgramImage> PageImage=programImageService.getAll(PageRequest.of(pageable, 10));
		long totalItems=PageImage.getTotalElements();
		int totalPages=PageImage.getTotalPages();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("programImages", PageImage);
		return "admin/ProgramImage/list";
	}
	@GetMapping("/edit/{id}")
	public String editForm(@PathVariable("id") Long id, Model model) {
	    ProgramImage programImage = programImageService.fillById(id);
	    model.addAttribute("programImage", programImage);
	    model.addAttribute("programs", programService.GetList());
	    return "admin/ProgramImage/edit";
	}
	@PostMapping("/update")
	public String update(
	        @ModelAttribute ProgramImage programImage,
	        @RequestParam("imagefile") MultipartFile file
	) throws IOException {

	    ProgramImage existing = programImageService.fillById(programImage.getId());

	    if (file != null && !file.isEmpty()) {

	        // Validate JPG/PNG
	        String contentType = file.getContentType();
	        if (!"image/jpeg".equals(contentType) && !"image/png".equals(contentType)) {
	            throw new RuntimeException("Chỉ cho phép upload ảnh JPG/PNG");
	        }

	        // Tạo thư mục nếu chưa tồn tại
	        Path uploadPath = Paths.get(uploadDir);
	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);
	        }

	        // Lấy tên file an toàn
	        String originalFileName = Paths.get(file.getOriginalFilename())
	                                       .getFileName()
	                                       .toString();

	        // Đặt tên tránh trùng
	        String fileName = System.currentTimeMillis() + "_" + originalFileName;
	        Path fileNameAndPath = uploadPath.resolve(fileName);

	        // Copy file
	        Files.copy(file.getInputStream(), fileNameAndPath, StandardCopyOption.REPLACE_EXISTING);

	        // Lưu đường dẫn giống save()
	        existing.setImageUrl("/uploads/programs/" + fileName);
	    }

	    // Cập nhật các field khác
	    existing.setIsThumbnail(programImage.getIsThumbnail());
	    existing.setProgram(programImage.getProgram());

	    programImageService.save(existing);

	    return "redirect:/admin/program-images/list";
	}
	@GetMapping("/list/{i}")
	public String listPage(@PathVariable("i") int page, Model model) {
		int pageable=Math.max(page-1,0);
		Page<ProgramImage> PageImage=programImageService.getAll(PageRequest.of(pageable, 10));
		long totalItems=PageImage.getTotalElements();
		int totalPages=PageImage.getTotalPages();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("programImages", PageImage);
		return "admin/ProgramImage/list";
	}
	@GetMapping("/create")
	public String CreateProgramImage(Model model) {
		ProgramImage programImage=new ProgramImage();
		model.addAttribute("programImage", programImage);
		model.addAttribute("programs", programService.GetList());
		return "admin/ProgramImage/create";
	}
	@PostMapping("/save")
	public String saveImages(@ModelAttribute("programImage") ProgramImage programImage, @RequestParam("imagefile") MultipartFile file)
			throws IllegalStateException, IOException {
		 if (file != null && !file.isEmpty()) {

		        // Validate jpg/png (optional nhưng nên có)
		        String contentType = file.getContentType();
		        if (!"image/jpeg".equals(contentType) && !"image/png".equals(contentType)) {
		            throw new RuntimeException("Chỉ cho phép upload ảnh JPG/PNG");
		        }

		        // Tạo thư mục nếu chưa tồn tại
		        Path uploadPath = Paths.get(uploadDir);
		        if (!Files.exists(uploadPath)) {
		            Files.createDirectories(uploadPath);
		        }

		        // Đặt tên file tránh trùng
		        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		        Path fileNameAndPath = uploadPath.resolve(fileName);

		        Files.copy(file.getInputStream(), fileNameAndPath, StandardCopyOption.REPLACE_EXISTING);

		        // Lưu URL web-friendly vào DB
		        programImage.setImageUrl("/uploads/programs/" + fileName);
		    }

		    this.programImageService.save(programImage);

		    return "redirect:/admin/programs/";
	}
	@GetMapping("/ping")
	@ResponseBody
	public String ping() {
	    return "OK";
	}
}
