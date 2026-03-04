package com.company.edu.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.company.edu.entity.Program;
import com.company.edu.entity.Registration;
import com.company.edu.repository.ProgramRepository;
import com.company.edu.repository.RegistrationRepository;

import jakarta.transaction.Transactional;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	@Autowired
	private RegistrationRepository registrationRepository;
	@Autowired
	private ProgramRepository programRepository;

	@Override
	public Registration save(Registration registration) {
		registration.setCreatedAt(LocalDateTime.now());
		registration.setStatus("PENDING");
		return registrationRepository.save(registration);
	}

	@Override
	public Registration findById(Long id) {
		return registrationRepository.findById(id).orElse(null);
	}

	@Override
	public void DeleteById(Long id) {
		registrationRepository.deleteById(id);

	}

	@Override
	public Page<Registration> GetAll(Pageable pageable) {

		return registrationRepository.findAll(pageable);
	}

	@Override
	public Page<Registration> findByStatus(String status, Pageable pageable) {

		return registrationRepository.findByStatus(status, pageable);
	}

	@Override
	@Transactional
	public void approve(Long id) {
		Registration registration = registrationRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Không tìm thấy đăng ký"));
		if (!"PENDING".equals(registration.getStatus())) {
			throw new RuntimeException("Đăng ký đã được xử lý");
		}

		Program program = registration.getProgram();

		if (program.getQuantity() <= 0) {
			throw new RuntimeException("Chương trình đã hết chỗ");
		}

		registration.setStatus("APPROVED");

		program.setQuantity(program.getQuantity() - 1);

		if (program.getQuantity() == 0) {
			program.setStatus("INACTIVE");
		}
		registrationRepository.save(registration);
		programRepository.save(program);

	}

	@Override
	@Transactional
	public void updateStatus(Long id, String status, String note) {

	    Registration registration = registrationRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Không tìm thấy đăng ký"));

	    String oldStatus = registration.getStatus();

	    if ("APPROVED".equalsIgnoreCase(status)) {

	        // Chỉ trừ khi từ PENDING -> APPROVED
	        if (!"APPROVED".equalsIgnoreCase(oldStatus)) {

	            Program program = registration.getProgram();

	            if (program.getQuantity() <= 0) {
	                throw new RuntimeException("Chương trình đã hết chỗ");
	            }

	            program.setQuantity(program.getQuantity() - 1);
	        }
	    }

	    registration.setStatus(status);

	    if (note != null && !note.trim().isEmpty()) {
	        registration.setNote(note);
	    }
	}

	

}
