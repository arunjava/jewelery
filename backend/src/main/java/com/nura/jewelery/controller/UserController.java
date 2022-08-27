package com.nura.jewelery.controller;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nura.jewelery.dto.UserDTO;
import com.nura.jewelery.dto.request.user.LoginDTO;
import com.nura.jewelery.entity.User;
import com.nura.jewelery.entity.UserRole;
import com.nura.jewelery.jwt.JwtTokenUtil;
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

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping(path = "/login")
	public ResponseEntity<ServiceResponse<UserDTO>> login(@RequestBody LoginDTO loginDTO) {

		User user = userService.findUserByUserName(loginDTO.getUsername());

		if (user != null && passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
			UserDTO userResponse = modelMapper.map(user, UserDTO.class);
			userResponse.setToken(jwtTokenUtil.generateToken(user.getUsername()));
			Set<UserRole> userRoles = user.getRoles();
			Set<String> roles = new HashSet<>();
			for (UserRole userRole : userRoles) {
				roles.add(userRole.getDesc());
			}

			userResponse.setRoles(roles);

			return ResponseEntity.ok(new ServiceResponseWrapper<UserDTO>().wrapServiceResponse(userResponse,
					"Valid User", HttpStatus.OK.value()));
		}

		return ResponseEntity.ok(new ServiceResponseWrapper<UserDTO>().wrapServiceResponse(null, "Not available",
				HttpStatus.NO_CONTENT.value()));
	}

//	@PostMapping(path = "/signup")
//	public ResponseEntity<ServiceResponse<?>> signup(@Valid @RequestBody UserDTO userDTO) {
//
//		User userExist = userService.findUserByUserName(userDTO.getUsername());
//
//		if (userExist != null) {
//			return ResponseEntity.ok(new ServiceResponseWrapper<String>().wrapServiceResponse(null,
//					"User Already Exist", HttpStatus.EXPECTATION_FAILED.value()));
//		}
//
//		User user = userService.saveUser(modelMapper.map(userDTO, User.class));
//
//		if (user != null) {
//			return ResponseEntity.ok(new ServiceResponseWrapper<String>().wrapServiceResponse(null,
//					"User Saved Successfully", HttpStatus.CREATED.value()));
//		}
//
//		return ResponseEntity.ok(new ServiceResponseWrapper<String>().wrapServiceResponse(null,
//				"Unable to save the details", HttpStatus.NOT_MODIFIED.value()));
//	}
}
