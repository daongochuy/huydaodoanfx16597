package com.company.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.company.edu.entity.User;
import com.company.edu.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncode;

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username).orElse(null);
	}

	@Override
	public Page<User> getAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User createUser(User user) {
		user.setPassword(passwordEncode.encode(user.getPassword()));
		if (user.getRole() == null || user.getRole().trim().isEmpty()) {
			user.setRole("ROLE_USER");
		}
		return userRepository.save(user);
	}

	@Override
	public User saveUser(User user) {
		user.setPassword(passwordEncode.encode(user.getPassword()));
		
		return userRepository.save(user);
	}

	@Override
	public Page<User> GetAllUser(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(long Id) {
		userRepository.deleteById(Id);

	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findByEmailAndMobileNumber(String email, String mobile) {
		// TODO Auto-generated method stub
		return userRepository.findByEmailAndMobile(email, mobile);
	}
	

	@Override
	public boolean checkEmail(String email) {
		return userRepository.existsByEmail(email);
	}

}
