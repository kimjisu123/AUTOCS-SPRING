package com.css.autocsfinal.Approval.repository;

import com.css.autocsfinal.Approval.entity.ApprovalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApproverRepository extends JpaRepository<ApprovalEntity, Integer> {
    List<ApprovalEntity> findByDocumentCode(int documentCode);

    List<ApprovalEntity> findByEmployeeNo(int employeeNo);
}
