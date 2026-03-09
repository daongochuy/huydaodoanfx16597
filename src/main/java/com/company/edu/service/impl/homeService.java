package com.company.edu.service.impl;

import java.util.List;
import java.util.Map;

import com.company.edu.entity.Category;
import com.company.edu.entity.Post;
import com.company.edu.entity.Program;

public interface homeService {
	 List<Category> getHomeCategories();
	 Map<Category, List<Program>> getHomeData();
	
}
