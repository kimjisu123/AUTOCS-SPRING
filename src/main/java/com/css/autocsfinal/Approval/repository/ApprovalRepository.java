package com.css.autocsfinal.Approval.repository;

import com.css.autocsfinal.Approval.entity.AppDeptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApprovalRepository extends JpaRepository<AppDeptEntity, String> {

    @Query(value = "select dept from AppDeptEntity dept where dept.upperDeptCode='B1'")
    List<AppDeptEntity> findByUpperDeptCode(String b1);
}
