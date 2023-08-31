package com.css.autocsfinal.mail.repository;

import com.css.autocsfinal.mail.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MailRepository extends JpaRepository<Mail, Integer> {
    @Query("SELECT m FROM Mail m WHERE m.status = :N")
    List<Mail> findByStatus(@Param("N") String N);


    List<Mail> findByPositionAndReceiver(String positionName, String name);

    List<Mail> findByPositionAndReceiverOrderByMailNoDesc(String positionName, String name);
}
