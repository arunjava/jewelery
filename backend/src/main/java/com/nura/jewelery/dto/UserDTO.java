package com.nura.jewelery.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class UserDTO {

	private long userId;
	private String firstName;
	private String middleName;
	private String lastName;
	private boolean isActive;
	private String username;
	private Set<String> roles = new HashSet<>();
	private String token;
	private String password;
}
