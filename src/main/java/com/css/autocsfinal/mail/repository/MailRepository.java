package com.css.autocsfinal.mail.repository;

import com.css.autocsfinal.mail.entity.Mail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MailRepository extends JpaRepository<Mail, Integer> {


    // 북마크 조회
    @Query("SELECT m FROM Mail m " +
            "WHERE m.position = :positionName " +
            "AND m.receiver = :name " +
            "AND m.status = :Y")
    List<Mail> findByStatus(String positionName, String name, @Param("Y") String Y, Pageable paging);

    // 북마크 총 갯수
    @Query("SELECT m FROM Mail m " +
            "WHERE m.position = :positionName " +
            "AND m.receiver = :name " +
            "AND m.status = :Y")
    List<Mail> findByStatus(String positionName, String name, @Param("Y") String Y);


    // 북마크 검색 갯수
    @Query("SELECT m FROM Mail m " +
            "WHERE m.position = :positionName " +
            "AND m.receiver = :name " +
            "AND m.status = :Y " +
            "AND m.title Like :title")
    List<Mail> findByStatus(String positionName, String name, @Param("Y") String Y, String title);

    @Query("SELECT m FROM Mail m " +
            "WHERE m.position = :positionName " +
            "AND m.receiver = :name " +
            "AND m.status = :Y " +
            "AND m.title Like :title")
    List<Mail> findByStatus(String positionName, String name, @Param("Y") String bookMark, String title, Pageable paging);

    @Query("SELECT m FROM Mail  m " +
            "WHERE m.position = :positionName " +
            "AND m.receiver = :name " +
            "AND m.read = :N ")
    List<Mail> findByRead(String positionName, String name, @Param("N") String notRead);


    List<Mail> findByPositionAndReceiverOrderByMailNoDesc(String positionName, String name);

    List<Mail> findByPositionAndReceiverOrderByMailNoDesc(String positionName, String name,Pageable paging);

    List<Mail> findByPositionAndReceiverAndTitleLikeOrderByMailNoDesc(String positionName, String name,Pageable paging, String title);

    List<Mail> findByPositionAndReceiverAndTitleLikeOrderByMailNoDesc(String positionName, String name, String title);

    void deleteByPositionAndReceiver(String positionName, String name);

    void deleteByMailNo(int mailNo);

}
