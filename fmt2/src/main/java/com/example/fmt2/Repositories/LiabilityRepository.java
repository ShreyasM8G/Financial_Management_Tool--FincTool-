package com.example.fmt2.Repositories;

import com.example.fmt2.Entities.Asset;
import com.example.fmt2.Entities.Liability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LiabilityRepository extends JpaRepository<Liability, Long> {
    List<Liability> findByUid(Long uid);
}
