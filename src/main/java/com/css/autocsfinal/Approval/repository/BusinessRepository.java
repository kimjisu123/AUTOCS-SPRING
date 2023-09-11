package com.css.autocsfinal.Approval.repository;

import com.css.autocsfinal.Approval.entity.BusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<BusinessEntity, Integer> {
    BusinessEntity findByDocumentCode(int documentCode);
}
