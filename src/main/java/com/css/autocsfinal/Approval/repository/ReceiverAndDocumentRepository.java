package com.css.autocsfinal.Approval.repository;

import com.css.autocsfinal.Approval.entity.ReceiveDocumentEntity;
import com.css.autocsfinal.Approval.entity.ReceiverDocumentEntityKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiverAndDocumentRepository extends JpaRepository<ReceiveDocumentEntity, ReceiverDocumentEntityKey> {
    Page<ReceiveDocumentEntity> findByKeyNo(int employeeNo, Pageable paging);

    Page<ReceiveDocumentEntity> findByKeyNoAndStatus(int employeeNo, String status, Pageable paging);
}
