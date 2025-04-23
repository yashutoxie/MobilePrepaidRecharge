package com.project.MobilePrepaidService.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.project.MobilePrepaidService.Entity.RechargeHistory;
import com.project.MobilePrepaidService.Entity.RechargePlan;
import com.project.MobilePrepaidService.Entity.User;
import com.project.MobilePrepaidService.repo.RechargeHistoryRepo;
import com.project.MobilePrepaidService.repo.RechargePlanRepo;
import com.project.MobilePrepaidService.repo.UserRepo;

@Service
public class RechargeService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RechargePlanRepo planRepo;
	@Autowired
	private RechargeHistoryRepo historyRepo;
	@Autowired
	private JavaMailSender mailSender;

	public String recharge(Long userId, Integer planId, String paymentMethod) {
		Optional<User> userOpt = userRepo.findById(userId);
		Optional<RechargePlan> planOpt = planRepo.findById(planId);
		if (userOpt.isEmpty() || planOpt.isEmpty()) {
			return "User or Plan not found.";
		}

		RechargeHistory history = new RechargeHistory();
		history.setUser(userOpt.get());
		history.setPlan(planOpt.get());
		history.setPaymentMethod(paymentMethod);
		history.setRechargeDate(LocalDateTime.now());
		historyRepo.save(history);

		sendConfirmationEmail(userOpt.get().getEmail(), planOpt.get());
		return "Recharge Successful";

	}
	
	private void sendConfirmationEmail(String to, RechargePlan plan) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject("Recharge Confirmation");
		message.setText("You have successfully recharged with plan: " + plan.getName()
	                + " | Price: ₹" + plan.getPrice()
	                + " | Validity: " + plan.getValidityInDays() + " days");
		mailSender.send(message);
	}
	
	public List<RechargeHistory> getUserHistory(Long userId){
		return historyRepo.findByUserId(userId);
	}

}
