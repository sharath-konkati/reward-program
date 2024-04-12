package com.assignments.service;

import com.assignments.dao.model.Transaction;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;

public interface RewardService {
    Map<Long, Map<YearMonth, Integer>> calculateRewardPointsPerMonth(List<Transaction> transactions);
}
