package com.project.MobilePrepaidService.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.MobilePrepaidService.Entity.RechargePlan;

public interface RechargePlanRepo extends JpaRepository<RechargePlan, Integer>{
	List<RechargePlan> findByCategory(String category);
}
