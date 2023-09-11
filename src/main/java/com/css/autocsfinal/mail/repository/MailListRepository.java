package com.css.autocsfinal.mail.repository;

import com.css.autocsfinal.common.Criteria;
import com.css.autocsfinal.mail.entity.MailList;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MailListRepository extends JpaRepository<MailList, Integer> {

    List<MailList> findByEmployeeNo(int employeeNo);

    @Query("select l from MailList l WHERE l.employeeNo = :employeeNo")
    List<MailList> findByPage(int employeeNo, Pageable paging);

    @Query("select l, m from MailList l JOIN l.mail m WHERE l.employeeNo = :employeeNo AND m.title LIKE %:title%")
    List<MailList> findByPage(int employeeNo, Pageable paging, String title);

    @Query("select l, m from MailList l JOIN l.mail m WHERE l.employeeNo = :employeeNo AND m.title LIKE %:title%")
    List<MailList> findByPage(int employeeNo, String title);
}