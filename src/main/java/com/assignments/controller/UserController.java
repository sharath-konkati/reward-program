package com.assignments.controller;

import com.assignments.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @Operation(summary = "Get user reward points", description = "Get reward points earned by a user per month")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User reward points retrieved",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Collection.class))}),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{userId}/rewards")
    public ResponseEntity<?> getUserRewardPoints(@PathVariable Long userId) {
        Map<Long, Map<YearMonth, Integer>> userRewardPoints = userService.getUserRewardPoints(userId);
        return ResponseEntity.ok().body(userRewardPoints.values());
    }
}
