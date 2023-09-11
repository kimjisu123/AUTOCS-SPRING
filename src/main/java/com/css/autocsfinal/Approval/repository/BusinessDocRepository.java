package com.css.autocsfinal.Approval.repository;

import com.css.autocsfinal.Approval.entity.BusinessDocEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BusinessDocRepository extends JpaRepository<BusinessDocEntity, Integer> {
    BusinessDocEntity findByDocumentCode(int documentCode);
}
