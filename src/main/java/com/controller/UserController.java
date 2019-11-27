package com.controller;



import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.User;
import com.service.UserService;

@RestController
@XmlRootElement
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/readall")
	List<User> readAll() {
		return userService.readUser();
	}

	@PostMapping(value = "/createuser")
	User createUser(@RequestBody User user) {

		return userService.createUser(user);
	}

	@GetMapping(value = "/readuserbyid/{alias}")
	User readUserById(@PathVariable("alias") int userId) {
		return userService.readUserById(userId);
	}

	@GetMapping(value = "/readuserbyname/{alias}")
	User readUserByName(@PathVariable("alias") String userName) {
		return userService.readUserByName(userName);
	}

	@DeleteMapping(value = "/deleteuser/{alias}")
	User deletebyuserId(@PathVariable("alias") int userId) {
		System.out.println("enter the userid to delete" + userId);
		return userService.deleteUserById(userId);
	}

	@PutMapping(value = "/updateuser")
	User updateuser(@RequestBody User user) {
		return userService.updateUser(user);
	}
}
