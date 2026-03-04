package com.company.edu.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.edu.entity.ProgramImage;
public interface ProgramImageRepository extends JpaRepository<ProgramImage, Long> {
	 List<ProgramImage> findByProgramId(Long programId);

	    Optional<ProgramImage> findByProgramIdAndIsThumbnailTrue(Long programId);
}
