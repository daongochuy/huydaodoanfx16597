package com.company.edu.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.company.edu.entity.Member;

public interface MemberService {
	Page<Member> getAll(Pageable pageable);

    Member findByEmail(String email);

    Member save(Member member);

    Member findById(Long id);
    void DeleteById(Long id);
}
