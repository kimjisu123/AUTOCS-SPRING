package com.css.autocsfinal.Approval.repository;

import com.css.autocsfinal.Approval.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Integer> {
    List<PurchaseEntity> findByDocumentCode(int documentCode);
}
