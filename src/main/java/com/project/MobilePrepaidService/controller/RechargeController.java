package com.project.MobilePrepaidService.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController; 
import com.project.MobilePrepaidService.Entity.RechargeHistory;
import com.project.MobilePrepaidService.service.RechargeService;

@RestController
public class RechargeController {
	private final RechargeService service;
	
	public RechargeController(RechargeService service) {
		this.service = service;
	}
	
	@PostMapping("/recharge")
	public String recharge(
			@RequestParam Long userId,
			@RequestParam Integer planId,
			@RequestParam String paymentMethod) {
		return service.recharge(userId, planId, paymentMethod);
	}
	
	@GetMapping("recharge/history/{userId}")
	public List<RechargeHistory> getHistory(@PathVariable Long userId){
		return service.getUserHistory(userId);
	}

}
