package com.company.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.company.edu.entity.Post;
import com.company.edu.repository.PostRepository;


@Service
public class PostServiceImpl implements PostService{
    @Autowired
	 private PostRepository postRepository;
	@Override
	public Page<Post> getAll(Pageable pageable) {
		return postRepository.findAll(pageable);
	}

	@Override
	public Page<Post> search(String title, Pageable pageable) {
		  return postRepository.findByTitleContainingIgnoreCase(title, pageable);
	}

	@Override
	public Post save(Post post) {
		return postRepository.save(post);
	}

	@Override
	public Post findById(Long id) {
		return postRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		 postRepository.deleteById(id);
		
	}

}
