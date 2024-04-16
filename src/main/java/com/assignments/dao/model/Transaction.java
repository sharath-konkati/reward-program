package com.assignments.dao.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The unique identifier for the transaction")
    private Long id;
    @Schema(description = "The amount of the transaction")
    private BigDecimal amount;
    @Schema(description = "The timestamp of the transaction")
    private LocalDateTime timestamp;
    @ManyToOne
    @Schema(description = "The user associated with the transaction")
    private User user;
}