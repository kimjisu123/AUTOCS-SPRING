package com.css.autocsfinal.mail.repository;

import com.css.autocsfinal.mail.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailRepository extends JpaRepository<Mail, Integer> {
    @Query("SELECT m FROM Mail m WHERE m.status = :N")
    List<Mail> findByStatus(@Param("N") String N);
}
