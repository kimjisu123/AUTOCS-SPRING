package com.css.autocsfinal.Approval.repository;

import com.css.autocsfinal.Approval.entity.PurchaseDocEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseDocRepository extends JpaRepository<PurchaseDocEntity, Integer> {
    PurchaseDocEntity findByDocumentCode(int documentCode);
}
