package com.css.autocsfinal.Approval.repository;

import com.css.autocsfinal.Approval.entity.DocumentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocumentRepository extends JpaRepository<DocumentEntity, Integer> {

    List<DocumentEntity> findByEmployeeNoOrderByDocumentCodeDesc(int employeeNo);
    List<DocumentEntity> findByEmployeeNo(int employeeNo);
    Page<DocumentEntity> findByEmployeeNo(int employeeNo, Pageable paging);
    List<DocumentEntity> findByEmployeeNoAndDocumentType(int employeeNo, String documentType);
    Page<DocumentEntity> findByEmployeeNoAndDocumentType(int employeeNo, Pageable paging, String documentType);
}
