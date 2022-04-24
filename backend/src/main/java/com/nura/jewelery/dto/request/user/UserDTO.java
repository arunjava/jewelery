package com.nura.jewelery.dto.request.user;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

	@NotEmpty(message = "{firstName.empty}")
	private String firstName;
	private String middleName;
	private String lastName;
	@NotEmpty(message = "{username.empty}")
	private String username;
	@NotEmpty(message = "{password.empty}")
	private String password;
}
