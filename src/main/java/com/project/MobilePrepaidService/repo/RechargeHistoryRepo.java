package com.project.MobilePrepaidService.repo; 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.MobilePrepaidService.Entity.RechargeHistory;

public interface RechargeHistoryRepo extends JpaRepository<RechargeHistory, Integer> {
	List<RechargeHistory> findByUserId(Long userId);
}
