package com.company.edu.controller.admin;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import org.springframework.web.multipart.MultipartFile;

import com.company.edu.entity.Category;
import com.company.edu.entity.Post;
import com.company.edu.service.impl.CategoryService;
import com.company.edu.service.impl.PostService;


@Controller
@RequestMapping("/admin/posts")
public class PostController {
	@Autowired
	private PostService postService;
    
	@GetMapping("/list")
	public String list(@RequestParam(defaultValue = "1") int page, Model model) {

	    int pageable = Math.max(page - 1, 0);

	    Page<Post> pagePost = postService.getAll(PageRequest.of(pageable, 10));

	    long totalItems = pagePost.getTotalElements();
	    int totalPages = pagePost.getTotalPages();

	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("totalItems", totalItems);
	    model.addAttribute("posts", pagePost);

	    return "admin/post/list";
	}
	@GetMapping("/list/{i}")
	public String listPage(@PathVariable("i") int page, Model model) {

	    int pageable = Math.max(page - 1, 0);

	    Page<Post> pagePost = postService.getAll(PageRequest.of(pageable, 10));

	    long totalItems = pagePost.getTotalElements();
	    int totalPages = pagePost.getTotalPages();

	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("totalItems", totalItems);
	    model.addAttribute("posts", pagePost);

	    return "admin/post/list";
	}
	@GetMapping("/create")
	public String createPost(Model model) {
		model.addAttribute("post", new Post());
		return "admin/post/create";
	}
	@PostMapping("/save")
	public String savePost(@ModelAttribute("post") Post post,
	                       @RequestParam("thumbnailFile") MultipartFile file) {

	    try {

	        // Nếu có upload ảnh mới
	        if (!file.isEmpty()) {

	            String uploadDir = "uploads/posts/";

	            File dir = new File(uploadDir);
	            if (!dir.exists()) {
	                dir.mkdirs();
	            }

	            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

	            Path path = Paths.get(uploadDir + fileName);

	            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

	            // lưu đường dẫn ảnh mới
	            post.setThumbnail("/uploads/posts/" + fileName);

	        } else {

	            // nếu không upload ảnh mới → giữ ảnh cũ
	            if (post.getId() != null) {
	                Post oldPost = postService.findById(post.getId());
	                if (oldPost != null) {
	                    post.setThumbnail(oldPost.getThumbnail());
	                    post.setCreatedAt(oldPost.getCreatedAt());
	                }
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    // nếu là bài viết mới thì set createdAt
	    if (post.getId() == null) {
	        post.setCreatedAt(LocalDateTime.now());
	    }

	    postService.save(post);

	    return "redirect:/admin/posts/list";
	}

	/*
	 * @PostMapping("/save") public String savePost(@ModelAttribute("post") Post
	 * post, @RequestParam("thumbnailFile") MultipartFile file) {
	 * 
	 * try {
	 * 
	 * if (!file.isEmpty()) {
	 * 
	 * String uploadDir = "uploads/posts/";
	 * 
	 * File dir = new File(uploadDir); if (!dir.exists()) { dir.mkdirs(); }
	 * 
	 * String fileName = System.currentTimeMillis() + "_" +
	 * file.getOriginalFilename();
	 * 
	 * Path path = Paths.get(uploadDir + fileName);
	 * 
	 * Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
	 * 
	 * // lưu đường dẫn vào database post.setThumbnail("/uploads/posts/" +
	 * fileName); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * post.setCreatedAt(LocalDateTime.now());
	 * 
	 * postService.save(post);
	 * 
	 * return "redirect:/admin/post/list"; }
	 */
	@GetMapping("/delete/{id}")
	public String deletePost(@PathVariable("id") Long id) {

		Post post = postService.findById(id);

		if (post != null) {

			try {

				// lấy đường dẫn ảnh
				String thumbnail = post.getThumbnail();

				if (thumbnail != null && !thumbnail.isEmpty()) {

					// bỏ "/uploads/"
					String filePath = thumbnail.replace("/uploads/", "uploads/");

					File file = new File(filePath);

					if (file.exists()) {
						file.delete();
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			postService.delete(id);
		}

		return "redirect:/admin/posts/list";
	}
	@GetMapping("/edit/{id}")
	public String UpdatePost(@PathVariable("id") long id,Model model)
	{   
		Post post=postService.findById(id);
		model.addAttribute("post", post);
		return "admin/post/createAndUpdate";
	}

}
