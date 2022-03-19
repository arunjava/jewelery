package com.nura.jewelery.dto.request.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
	
	private String firstName;
	private String middleName;
	private String lastName;
	private String username;
	private String password;
}
