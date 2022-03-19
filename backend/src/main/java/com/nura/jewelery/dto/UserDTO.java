package com.nura.jewelery.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserDTO {

	@NotNull
	private String firstName;
	private String middleName;
	private String lastName;
	private String emailID;

}
