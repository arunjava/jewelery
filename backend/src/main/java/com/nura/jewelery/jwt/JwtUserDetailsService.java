package com.nura.jewelery.jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nura.jewelery.entity.User;
import com.nura.jewelery.entity.UserRole;
import com.nura.jewelery.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with name :" + username);
		}

		Collection<? extends GrantedAuthority> roles = getGrantedAuthorities(user.getRoles());
		UserDetails userDtls = new org.springframework.security.core.userdetails.User(user.getUsername(),
				user.getPassword(), roles);
		System.out.println(userDtls);
		return userDtls;
	}

	private List<GrantedAuthority> getGrantedAuthorities(Set<UserRole> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (UserRole role : roles) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
		}
		return authorities;
	}

}
