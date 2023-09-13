package com.css.autocsfinal.Approval.repository;

import com.css.autocsfinal.Approval.entity.PayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayRepository extends JpaRepository<PayEntity, Integer> {
    List<PayEntity> findByDocumentCode(int documentCode);

    void deleteByDocumentCode(int documentCode);
}
