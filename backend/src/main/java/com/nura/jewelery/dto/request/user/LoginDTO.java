package com.nura.jewelery.dto.request.user;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDTO {

	@NotEmpty(message = "{username.empty}")
	private String username;
	@NotEmpty
	private String password;

}
