package com.nura.textiles.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nura.textiles.entity.user.User;
import com.nura.textiles.repository.UserRepository;
import com.nura.textiles.utils.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.getUserByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		
		return new UserDetailsImpl(user);
	}

}
