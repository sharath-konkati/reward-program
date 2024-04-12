package com.assignments.controller;

import com.assignments.dao.model.Transaction;
import com.assignments.dao.model.User;
import com.assignments.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetRewardPointsForUser() throws Exception {
        // Mock user ID and transactions
        Long userId = 1L;
        List<Transaction> transactions = Arrays.asList(
                createTransaction(BigDecimal.valueOf(120)),  // $120 purchase
                createTransaction(BigDecimal.valueOf(80)),   // $80 purchase
                createTransaction(BigDecimal.valueOf(30))    // $30 purchase
        );
        //get user
        User user = new User(1l, "ABC", "");
        when(userService.getUserRewardPoints(1l)).thenReturn(Map.of(1l, Map.of(YearMonth.now(), 2)));

        // Perform GET request to "/{userId}/rewards"
        mockMvc.perform(get("/users/{userId}/rewards", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // Helper method to create a Transaction object with a given amount
    private Transaction createTransaction(BigDecimal amount) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        return transaction;
    }
}
