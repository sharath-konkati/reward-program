package com.assignments.service.impl;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RewardServiceTest {

    @Test
    public void testCalculateRewardPointsForTransaction() {
        RewardServiceImpl rewardService = new RewardServiceImpl();

        // Test case: $120 purchase
        BigDecimal amount120 = BigDecimal.valueOf(120);
        int expectedPoints120 = 90; // 2 * $20 + 1 * $50 = 90 points
        int actualPoints120 = rewardService.calculateRewardPointsForTransaction(amount120);
        assertEquals(expectedPoints120, actualPoints120);

        // Test case: $80 purchase
        BigDecimal amount80 = BigDecimal.valueOf(80);
        int expectedPoints80 = 30; // 1 * $30 = 30 points
        int actualPoints80 = rewardService.calculateRewardPointsForTransaction(amount80);
        assertEquals(expectedPoints80, actualPoints80);

        // Test case: $30 purchase
        BigDecimal amount30 = BigDecimal.valueOf(30);
        int expectedPoints30 = 0; // No points earned
        int actualPoints30 = rewardService.calculateRewardPointsForTransaction(amount30);
        assertEquals(expectedPoints30, actualPoints30);
    }
}

