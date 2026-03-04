package com.company.edu.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.company.edu.entity.Program;
import com.company.edu.entity.ProgramImage;
import com.company.edu.repository.ProgramImageRepository;
import com.company.edu.repository.ProgramRepository;

@Service
public class ProgramServiceImpl implements ProgramService {
	@Autowired
	private ProgramRepository programRepository;
	@Autowired
	private ProgramImageRepository programImageRepository;

	@Override
	public Page<Program> getAll(Pageable pageable) {
		return programRepository.findAll(pageable);
	}

	@Override
	public Program save(Program program) {
		return programRepository.save(program);
	}

	@Override
	public Program findById(long id) {
		return programRepository.findById(id).orElse(null);
	}

	@Override
	public void DeleteById(long id) {
		if (!programRepository.existsById(id)) {
			throw new RuntimeException("Program not found with id = " + id);
		} else {
			List<ProgramImage> images = programImageRepository.findByProgramId(id);

			images.forEach(img -> deleteFile(img.getImageUrl()));
			programRepository.deleteById(id);
		}

	}

	@Override
	public Page<Program> search(String Keyword, Pageable pageable) {
		return programRepository.searchPage(Keyword, pageable);
	}

	@Override
	public List<Program> GetList() {
		return programRepository.findAll();
	}

	@Override
	public void DeleteByList(long[] listId) {
		for (int i = 0; i < listId.length; i++) {
			if (!programRepository.existsById(listId[i])) {
				throw new RuntimeException("Program not found with id = " + listId[i]);
			} else {
				List<ProgramImage> images = programImageRepository.findByProgramId(listId[i]);

				images.forEach(img -> deleteFile(img.getImageUrl()));
				programRepository.deleteById(listId[i]);
			}
		}

	}

	@Override
	public void DeleteAll() {
		// 1. Lấy toàn bộ ảnh trong DB
		List<ProgramImage> images = programImageRepository.findAll();

		// 2. Xóa file vật lý
		images.forEach(img -> deleteFile(img.getImageUrl()));

		// 3. Xóa DB
		programRepository.deleteAll();
		// hoặc programImageRepository.deleteAll(); nếu không cascade

	}

	@Override
	public void deleteFile(String imageUrl) {
		try {
			if (imageUrl == null || imageUrl.isBlank())
				return;

			// /uploads/programs/abc.jpg -> uploads/programs/abc.jpg
			if (imageUrl.startsWith("/")) {
				imageUrl = imageUrl.substring(1);
			}

			// Thư mục root của project (projectName/)
			String projectDir = System.getProperty("user.dir");

			// Ghép thành đường dẫn thật trên ổ đĩa
			Path path = Paths.get(projectDir, imageUrl);

			Files.deleteIfExists(path);

			System.out.println("✅ Deleted file: " + path.toAbsolutePath());

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("❌ Không xóa được file: " + imageUrl);
		}

	}

	@Override
	public Page<Program> findByCategoryId(Long categoryId,Pageable pageable) {
		
		return programRepository.findByCategoryId(categoryId, pageable);
	}

	

}
