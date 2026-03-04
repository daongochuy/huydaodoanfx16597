package com.company.edu.service.impl;
import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.company.edu.entity.ProgramImage;
public interface ProgramImageService {
	Page<ProgramImage> getAll(Pageable pageable);
	ProgramImage fillById(Long id);
	List<ProgramImage> getByProgram(Long programId);
	String getThumbnailUrl(long programId);
    ProgramImage save(ProgramImage image);

    void delete(Long id);

}
