package com.project.MobilePrepaidService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.MobilePrepaidService.Entity.User;
import com.project.MobilePrepaidService.service.UserService;

@RestController
public class UserController {
	@Autowired

	private UserService userService;

	@PostMapping("/users")
	public User insert(@RequestBody User user) {
		return userService.insert(user);
	}

	@GetMapping("/users")
	public List<User> fetchAllUsers() {
		return userService.fetchAllUsers();
	}

}
