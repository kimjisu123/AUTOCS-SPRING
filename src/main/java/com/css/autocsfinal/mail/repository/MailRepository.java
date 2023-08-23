package com.css.autocsfinal.mail.repository;

import com.css.autocsfinal.mail.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends JpaRepository<Mail, Integer> {
}
