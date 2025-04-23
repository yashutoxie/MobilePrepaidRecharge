package com.project.MobilePrepaidService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.MobilePrepaidService.Entity.User;
import com.project.MobilePrepaidService.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	UserRepo userRepo;
	
	public User insert(User user) {
		return userRepo.save(user);
	}
	public List<User> fetchAllUsers(){
		List<User> users = userRepo.findAll();
		return users;
	}
	

}
