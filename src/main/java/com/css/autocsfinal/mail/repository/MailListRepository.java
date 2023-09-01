package com.css.autocsfinal.mail.repository;

import com.css.autocsfinal.mail.entity.MailList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MailListRepository extends JpaRepository<MailList, Integer> {

    List<MailList> findByEmployeeNo(int employeeNo);
}