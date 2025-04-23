package com.project.MobilePrepaidService.service;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.MobilePrepaidService.Entity.*;
import com.project.MobilePrepaidService.repo.RechargePlanRepo;

@Service
public class RechargePlanService {
	
	@Autowired
	private RechargePlanRepo repo;
	
	
	public List<RechargePlan> getAllPlans(){
		return repo.findAll();
	}
	
	public List<RechargePlan> getPlansByCategory(String category){
		return repo.findByCategory(category);
	}
	
	public RechargePlan insert(RechargePlan rechargePlan) {
		return repo.save(rechargePlan);
	}
	
	

}
