package com.nura.jewelery.service;

import com.nura.jewelery.entity.user.User;

public interface UserService {

	public User saveUser(User user);

	public User findUserByUserName(String loginId);

}
