package com.company.edu.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.company.edu.entity.ProgramImage;
import com.company.edu.repository.ProgramImageRepository;

@Service
public class ProgramImageServiceImpl implements ProgramImageService {
	@Autowired
	private ProgramImageRepository imageRepository;

	@Override
	public List<ProgramImage> getByProgram(Long programId) {
		  return imageRepository.findByProgramId(programId);
	}

	@Override
	public ProgramImage save(ProgramImage image) {
		 image.setCreatedAt(LocalDateTime.now());
	        return imageRepository.save(image);
	}

	@Override
	public void delete(Long id) {
		  imageRepository.deleteById(id);
		
	}

	@Override
	public String getThumbnailUrl(long programId) {
		  return imageRepository.findByProgramIdAndIsThumbnailTrue(programId)
		            .map(ProgramImage::getImageUrl)
		            .orElse("/uploads/programs/no-image.png");
	}

	@Override
	public Page<ProgramImage> getAll(Pageable pageable) {
		return imageRepository.findAll(pageable);
	}

	@Override
	public ProgramImage fillById(Long id) {
		
		 return imageRepository.findById(id)
		            .orElseThrow(() -> new RuntimeException("Không tìm thấy ảnh với id: " + id));
	}
}
