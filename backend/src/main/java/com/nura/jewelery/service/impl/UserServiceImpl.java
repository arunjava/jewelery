package com.nura.jewelery.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nura.jewelery.entity.User;
import com.nura.jewelery.entity.UserRole;
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

	private List<GrantedAuthority> getGrantedAuthorities(Set<UserRole> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (UserRole role : roles) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
		}
		return authorities;
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