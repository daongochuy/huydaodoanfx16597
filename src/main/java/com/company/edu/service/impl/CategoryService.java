package com.company.edu.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.company.edu.entity.Category;

public interface CategoryService {
	
	   Page<Category> getAll(Pageable pageable);

	    List<Category> getActive();
	    
	    List<Category> findByType(String type);

	    Category save(Category category);

	    Category findById(Long id);
	    
	    void deleteById(Long id);
}
