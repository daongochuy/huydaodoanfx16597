package com.company.edu.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.company.edu.entity.Contact;
public interface ContactRepository extends JpaRepository<Contact, Long>{
	 Page<Contact> findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
	            String fullName,
	            String email,
	            Pageable pageable);

	    Page<Contact> findByStatus(String status, Pageable pageable);
}
