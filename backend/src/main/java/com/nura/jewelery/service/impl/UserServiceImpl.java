package com.nura.jewelery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nura.jewelery.entity.User;
import com.nura.jewelery.repository.UserRepository;
import com.nura.jewelery.service.UserService;

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

	/**
	 * Spring security for User
	 */
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		final User user = userRepo.getUserByUsername(username);
//		if (user == null) {
//			throw new UsernameNotFoundException(username);
//		}
//
//		UserDetails userDtls = org.springframework.security.core.userdetails.User.withUsername(username)
//				.password(user.getPassword()).authorities("ROLE_USER").build();
//		System.out.println(userDtls.toString());
//		return userDtls;
//
//	}

}
