package com.css.autocsfinal.Approval.repository;

import com.css.autocsfinal.Approval.entity.ApprovalAndDocumentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalAndDocumentRepository extends JpaRepository<ApprovalAndDocumentEntity, Integer> {
    Page<ApprovalAndDocumentEntity> findByEmployeeNo(int employeeNo, Pageable paging);
    Page<ApprovalAndDocumentEntity> findByEmployeeNoAndStatus(int employeeNo, String status, Pageable paging);
}
