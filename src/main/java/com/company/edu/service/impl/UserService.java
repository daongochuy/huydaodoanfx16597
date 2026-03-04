package com.company.edu.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.company.edu.entity.User;

public interface UserService {
	User findByUsername(String username);

	Page<User> getAll(Pageable pageable);

	User findById(Long id);

	User createUser(User user);

	User saveUser(User user);

	Page<User> GetAllUser(int page);

	void deleteById(long Id);


	User findByEmail(String email);

	User findByEmailAndMobileNumber(String email, String mobile);

	public boolean checkEmail(String email);
}
