package com.project.MobilePrepaidService.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.MobilePrepaidService.Entity.RechargeHistory;
import com.project.MobilePrepaidService.Entity.User;
import com.project.MobilePrepaidService.repo.RechargeHistoryRepo;
import com.project.MobilePrepaidService.repo.UserRepo;

@Service
public class AdminService {
	@Autowired
	RechargeHistoryRepo rechargeHistoryRepo;

	@Autowired
	UserRepo userRepo;

	public List<User> getAllUsersExpiring() {
		List<User> users = userRepo.findAll();
		List<User> usersExpiring= new ArrayList<>();

		for (User u : users) {
			List<RechargeHistory> recharges = rechargeHistoryRepo.findByUserId(u.getId());
			if (!recharges.isEmpty()) {
				RechargeHistory thatmightexpire = recharges.get(recharges.size() - 1);
				LocalDateTime paymentdate = thatmightexpire.getRechargeDate();
				LocalDateTime expirydate = paymentdate.plusDays(thatmightexpire.getPlan().getValidityInDays());

				LocalDateTime todaysDate = LocalDateTime.now();

				if (!expirydate.isBefore(todaysDate) && expirydate.isBefore(todaysDate.plusDays(4))) {
					usersExpiring.add(u);

				}

			}
		}
		return usersExpiring;
	}

}
