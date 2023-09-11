package com.css.autocsfinal.Approval.repository;

import com.css.autocsfinal.Approval.entity.ReceiverEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceiveRepository extends JpaRepository<ReceiverEntity, Integer> {
    List<ReceiverEntity> findByEmployeeNo(int employeeNo);

    List<ReceiverEntity> findByEmployeeNoAndStatus(int employeeNo, String status);
}
