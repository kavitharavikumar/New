package com.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.model.User;

@Transactional
public interface UserService {
	 public abstract User createUser(User user);
	  public abstract List<User> readUser();
	  public abstract User readUserById(int userId);
	  public abstract User readUserByName(String userName);
	  public abstract User updateUser(User user);
	  public abstract User deleteUserById(int userId);
}
