package com.nura.textiles.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nura.textiles.entity.user.User;
import com.nura.textiles.repository.UserRepository;
import com.nura.textiles.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	public User saveUser(User user) {
		user.setPassword(encryptUserPassword(user.getPassword()));
		return userRepo.save(user);
	}

	@Override
	public User findUserByUserName(String username) {
		return userRepo.getUserByUsername(username);
	}

	private String encryptUserPassword(String password) {
		return passwordEncoder.encode(password);
	}

}
