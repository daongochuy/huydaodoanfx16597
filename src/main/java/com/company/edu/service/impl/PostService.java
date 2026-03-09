package com.company.edu.service.impl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.company.edu.entity.Post;
public interface PostService {
	Page<Post> getAll(Pageable pageable);

    Page<Post> search(String title, Pageable pageable);

    Post save(Post post);

    Post findById(Long id);
    
    public Post getPostByCategory(String category);

    void delete(Long id);
}
