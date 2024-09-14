package com.example.fmt2.Repositories;

import com.example.fmt2.Entities.Asset;
import com.example.fmt2.Entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUid(Long uid);
}
