package com.company.edu.repository;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.company.edu.entity.Post;
public interface PostRepository extends JpaRepository<Post, Long> {
	Page<Post> findByTitleContainingIgnoreCase(
            String title, Pageable pageable);
	Page<Post> findByCategory(
            String category, Pageable pageable);
	
	Optional<Post> findFirstByCategoryAndEnable(String category,  Boolean enabled);
}
