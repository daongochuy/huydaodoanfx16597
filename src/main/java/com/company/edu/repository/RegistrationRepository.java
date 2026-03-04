package com.company.edu.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.company.edu.entity.Registration;
public interface RegistrationRepository extends JpaRepository<Registration, Long>{
	
	 List<Registration> findByProgramId(Long programId);
	 Page<Registration> findByStatus(String status, Pageable pageable);
	 List<Registration> findByStatus(String status);
	 long countByStatus(String status);

}
