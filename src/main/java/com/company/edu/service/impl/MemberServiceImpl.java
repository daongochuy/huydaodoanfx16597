package com.company.edu.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.company.edu.entity.Member;
import com.company.edu.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberRepository memberRepository;
	@Override
	public Page<Member> getAll(Pageable pageable) {
		return memberRepository.findAll(pageable);
	}

	@Override
	public Member findByEmail(String email) {
		 return memberRepository.findByEmail(email).orElse(null);
	}

	@Override
	public Member save(Member member) {
		 member.setCreatedAt(LocalDateTime.now());
	        return memberRepository.save(member);
	}

	@Override
	public Member findById(Long id) {
		  return memberRepository.findById(id).orElse(null);
	}

	@Override
	public void DeleteById(Long id) {
		  memberRepository.deleteById(id);
		
	}

}
