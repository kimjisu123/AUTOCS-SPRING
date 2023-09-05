package com.css.autocsfinal.Approval.repository;

import com.css.autocsfinal.Approval.entity.DocumentFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentFileRepository extends JpaRepository<DocumentFileEntity, Integer> {
    List<DocumentFileEntity> findByDocumentCode(int documentCode);
}
