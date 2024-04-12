package com.assignments.controller;

import com.assignments.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}/rewards")
    public Collection<Map<YearMonth, Integer>> getUserRewardPoints(@PathVariable Long userId) {
        Map<Long, Map<YearMonth, Integer>> userRewardPoints = userService.getUserRewardPoints(userId);
        return userRewardPoints.values();
    }
}
