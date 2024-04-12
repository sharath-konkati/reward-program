package com.assignments.service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Map;

public interface UserService {

    Map<Long, Map<YearMonth, Integer>> getUserRewardPoints(Long id);
}
