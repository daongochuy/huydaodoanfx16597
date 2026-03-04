package com.company.edu.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.company.edu.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	       Optional<Member> findByEmail(String email);

	    Page<Member> findByFullNameContainingIgnoreCase(
	            String fullName, Pageable pageable);
	  
	    boolean existsByEmail(String email);
}
