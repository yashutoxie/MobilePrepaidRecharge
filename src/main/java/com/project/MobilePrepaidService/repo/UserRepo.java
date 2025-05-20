package com.project.MobilePrepaidService.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.MobilePrepaidService.Entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
	Optional<User> findByMobile(String mobile);
	Optional<User> findByEmail(String email);
	boolean existsByEmail(String email);
}
