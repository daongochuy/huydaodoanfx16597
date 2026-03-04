package com.company.edu.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.edu.entity.Category;
import com.company.edu.entity.Program;
import com.company.edu.repository.CategoryRepository;
import com.company.edu.repository.ProgramRepository;

@Service
public class homeServiceImpl implements homeService {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProgramRepository programRepository;
	@Override
	public List<Category> getHomeCategories() {
		return categoryRepository.findAllWithPrograms();
	}

	@Override
	public Map<Category, List<Program>> getHomeData() {
		 List<Program> programs = programRepository.findActivePrograms();

		    return programs.stream()
		            .collect(Collectors.groupingBy(Program::getCategory));
	}
	


}
