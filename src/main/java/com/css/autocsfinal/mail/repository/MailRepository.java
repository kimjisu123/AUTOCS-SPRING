package com.css.autocsfinal.mail.repository;

import com.css.autocsfinal.mail.entity.Mail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MailRepository extends JpaRepository<Mail, Integer> {
    @Query("SELECT m FROM Mail m WHERE m.status = :Y")
    List<Mail> findByStatus(@Param("Y") String Y);

    @Query("SELECT m FROM Mail m WHERE m.status = :Y")
    List<Mail> findByStatus(@Param("Y") String Y, Pageable paging);

    @Query("SELECT m FROM Mail m WHERE m.status = :Y AND m.title Like %:title%")
    List<Mail> findByStatus(@Param("Y") String N, Pageable paging, String title);


    List<Mail> findByPositionAndReceiverOrderByMailNoDesc(String positionName, String name);

    List<Mail> findByPositionAndReceiverOrderByMailNoDesc(String positionName, String name,Pageable paging);


}
