package com.css.autocsfinal.mail.repository;

import com.css.autocsfinal.mail.entity.Mail;
import com.css.autocsfinal.mail.entity.MailList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MailListRepository extends JpaRepository<MailList, Integer> {

    List<Mail> findMailListByEmployeeNo(int employeeNo);
}