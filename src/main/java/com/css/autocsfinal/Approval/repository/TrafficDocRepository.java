package com.css.autocsfinal.Approval.repository;

import com.css.autocsfinal.Approval.entity.TrafficDocEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrafficDocRepository extends JpaRepository<TrafficDocEntity, Integer> {
    TrafficDocEntity findByDocumentCode(int documentCode);
}
