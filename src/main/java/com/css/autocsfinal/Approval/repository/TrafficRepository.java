package com.css.autocsfinal.Approval.repository;

import com.css.autocsfinal.Approval.entity.TrafficEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrafficRepository extends JpaRepository<TrafficEntity, Integer> {
    List<TrafficEntity> findByDocumentCode(int documentCode);
}
