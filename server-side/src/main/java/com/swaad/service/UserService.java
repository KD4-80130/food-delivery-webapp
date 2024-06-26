package com.swaad.service;

import com.swaad.entities.User;

public interface UserService {

	public User findUserByJwtToken(String jwt) throws Exception;

	public User findUserByEmail(String email) throws Exception;
}
