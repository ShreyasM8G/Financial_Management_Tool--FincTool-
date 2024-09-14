package com.example.fmt2.Repositories;

import com.example.fmt2.Entities.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset,Long> {
    List<Asset> findByUid(Long uid);
}
