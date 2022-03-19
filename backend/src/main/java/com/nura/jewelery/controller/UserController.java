package com.nura.jewelery.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nura.jewelery.dto.request.user.UserDTO;
import com.nura.jewelery.entity.user.User;
import com.nura.jewelery.service.UserService;
import com.nura.jewelery.utils.ServiceResponse;
import com.nura.jewelery.utils.ServiceResponseWrapper;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping(path = "/login")
	public ResponseEntity<ServiceResponse<String>> login(@RequestParam String username, @RequestParam String password) {
		if (username.equalsIgnoreCase("arun") && password.equalsIgnoreCase("arun")) {
			return ResponseEntity.ok(
					new ServiceResponseWrapper<String>().wrapServiceResponse("", "ValidUser", HttpStatus.OK.value()));
		}

		return ResponseEntity.ok(new ServiceResponseWrapper<String>().wrapServiceResponse(null, "Not available",
				HttpStatus.NO_CONTENT.value()));
	}

	@PostMapping(path = "/signup")
	public ResponseEntity<ServiceResponse<?>> signup(@RequestBody UserDTO userDTO) {

		User userExist = userService.findUserByUserName(userDTO.getUsername());

		if (userExist != null) {
			return ResponseEntity.ok(new ServiceResponseWrapper<String>().wrapServiceResponse(null,
					"User Already Exist", HttpStatus.EXPECTATION_FAILED.value()));
		}

		User user = userService.saveUser(modelMapper.map(userDTO, User.class));

		if (user != null) {
			return ResponseEntity.ok(new ServiceResponseWrapper<String>().wrapServiceResponse(null,
					"User Saved Successfully", HttpStatus.CREATED.value()));
		}

		return ResponseEntity.ok(new ServiceResponseWrapper<String>().wrapServiceResponse(null,
				"Unable to save the details", HttpStatus.NOT_MODIFIED.value()));
	}
}
