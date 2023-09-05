package com.css.autocsfinal.Approval.repository;

import com.css.autocsfinal.Approval.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocumentRepository extends JpaRepository<DocumentEntity, Integer> {

    List<DocumentEntity> findByEmployeeNoOrderByDocumentCodeDesc(int employeeNo);
}
