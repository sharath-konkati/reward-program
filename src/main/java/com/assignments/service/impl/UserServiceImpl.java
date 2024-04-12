package com.assignments.service.impl;

import com.assignments.dao.model.Transaction;
import com.assignments.dao.model.User;
import com.assignments.repository.TransactionRepository;
import com.assignments.repository.UserRepository;
import com.assignments.service.RewardService;
import com.assignments.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final RewardService rewardService;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public Map<Long, Map<YearMonth, Integer>> getUserRewardPoints(Long userId) {
        // Get the current date
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Calculate the date three months ago
        LocalDateTime threeMonthsAgoDateTime = currentDateTime.minusMonths(3);

        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<Transaction> userTransactions = transactionRepository.findByUserAndTimestampAfter(user, threeMonthsAgoDateTime);
        return rewardService.calculateRewardPointsPerMonth(userTransactions);
    }
}
