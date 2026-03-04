package com.company.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.company.edu.entity.Category;
import com.company.edu.repository.CategoryRepository;


@Service
public class CategoryServiceImpl implements CategoryService{
    
	@Autowired
	private CategoryRepository categoryRepository;
	@Override
	public Page<Category> getAll(Pageable pageable) {
		 return categoryRepository.findAll(pageable);
	}

	@Override
	public List<Category> getActive() {
		 return categoryRepository.findByStatus("ACTIVE");	}

	@Override
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category findById(Long id) {
		return categoryRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long id) {
		categoryRepository.deleteById(id);
		
	}

	@Override
	public List<Category> findByType(String type) {
		
		return categoryRepository.findByType(type);
	}

	
}
