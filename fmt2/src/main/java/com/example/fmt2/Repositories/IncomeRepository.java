package com.example.fmt2.Repositories;

import com.example.fmt2.Entities.Asset;
import com.example.fmt2.Entities.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByUid(Long uid);
}
