package com.company.edu.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.company.edu.entity.Program;

public interface ProgramService {
	  Page<Program> getAll(Pageable pageable);
	  List<Program> GetList();
	  Page<Program> search(String Keyword, Pageable pageable);
	  Program save(Program program);
	  Program findById(long id);
	  void DeleteById(long id);
	  void DeleteByList(long[] listId);
	  void DeleteAll();
	  void deleteFile(String imageUrl);
	  Page<Program> findByCategoryId(Long categoryId,Pageable pageable);
	 
}
