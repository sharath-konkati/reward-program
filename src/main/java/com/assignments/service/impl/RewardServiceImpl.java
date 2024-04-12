package com.assignments.service.impl;

import com.assignments.dao.model.Transaction;
import com.assignments.service.RewardService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RewardServiceImpl implements RewardService {

    public Map<Long, Map<YearMonth, Integer>> calculateRewardPointsPerMonth(List<Transaction> transactions) {
        // Map to store reward points per customer per month
        Map<Long, Map<YearMonth, Integer>> rewardPointsPerCustomerPerMonth = new HashMap<>();

        for (Transaction transaction : transactions) {
            Long customerId = transaction.getUser().getId();
            LocalDate transactionDate = transaction.getTimestamp().toLocalDate();
            YearMonth yearMonth = YearMonth.from(transactionDate);

            // Calculate reward points for the transaction
            int rewardPoints = calculateRewardPointsForTransaction(transaction.getAmount());

            // Get the map for the customer
            Map<YearMonth, Integer> customerRewardPoints = rewardPointsPerCustomerPerMonth.computeIfAbsent(customerId, k -> new HashMap<>());

            // Update the reward points for the customer in the specific month
            int updatedRewardPoints = customerRewardPoints.getOrDefault(yearMonth, 0) + rewardPoints;
            customerRewardPoints.put(yearMonth, updatedRewardPoints);
        }

        return rewardPointsPerCustomerPerMonth;
    }

    public int calculateRewardPointsForTransaction(BigDecimal amount) {
        // Calculate amount exceeding thresholds
        BigDecimal amountOver100 = amount.subtract(BigDecimal.valueOf(100)).max(BigDecimal.ZERO); //140
        BigDecimal amountOver50 = amount.subtract(BigDecimal.valueOf(50)).max(BigDecimal.ZERO).min(BigDecimal.valueOf(100));//100


        // Calculate points for each tier
        int pointsOver100 = amountOver100.multiply(BigDecimal.valueOf(2)).intValue();
        int pointsOver50 = Math.min(amountOver50.intValue(), 50);  // Apply the cap

        // Calculate total reward points
        int totalPoints = pointsOver100 + pointsOver50;
        return totalPoints;
    }
}
