package com.css.autocsfinal.Approval.repository;

import com.css.autocsfinal.Approval.entity.ApprovalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApproverRepository extends JpaRepository<ApprovalEntity, Integer> {
}
