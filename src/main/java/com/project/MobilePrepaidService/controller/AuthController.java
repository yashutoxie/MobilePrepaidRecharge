package com.project.MobilePrepaidService.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.project.MobilePrepaidService.Entity.User;
import com.project.MobilePrepaidService.Exception.AuthenticationException;
import com.project.MobilePrepaidService.Exception.UserException;
import com.project.MobilePrepaidService.repo.UserRepo;
import com.project.MobilePrepaidService.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/register")
	public String register(@RequestBody User user) {
		if (!StringUtils.hasText(user.getEmail())) {
			throw new UserException("Email is required.");
		}

		if (!StringUtils.hasText(user.getPassword())) {
			throw new UserException("Password is required.");
		}

		if (userRepo.existsByEmail(user.getEmail())) {
			throw new UserException("Email already registered: " + user.getEmail());
		}

		user.setPassword(encoder.encode(user.getPassword()));
		userRepo.save(user);
		return "User Registered!";
	}

	@PostMapping("/login")
	public Map<String, String> login(@RequestBody User loginUser) {
		if (!StringUtils.hasText(loginUser.getEmail())) {
			throw new AuthenticationException("Email is required for login.");
		}

		if (!StringUtils.hasText(loginUser.getPassword())) {
			throw new AuthenticationException("Password is required for login.");
		}

		var user = userRepo.findByEmail(loginUser.getEmail())
				.orElseThrow(() -> new AuthenticationException("User not found with email: " + loginUser.getEmail()));

		if (!encoder.matches(loginUser.getPassword(), user.getPassword())) {
			throw new AuthenticationException("Invalid credentials for email: " + loginUser.getEmail());
		}

		String token = jwtUtil.generateToken(user.getEmail());
		return Map.of("token", token, "role", user.getRole().name());
	}
}
