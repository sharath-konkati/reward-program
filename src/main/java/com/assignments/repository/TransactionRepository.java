package com.assignments.repository;

import com.assignments.dao.model.Transaction;
import com.assignments.dao.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);

    List<Transaction> findByUserAndTimestampAfter(User user, LocalDateTime date);

}
