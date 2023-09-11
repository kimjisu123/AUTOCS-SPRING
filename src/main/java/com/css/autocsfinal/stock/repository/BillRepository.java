package com.css.autocsfinal.stock.repository;

import com.css.autocsfinal.stock.entity.Bill;
import com.css.autocsfinal.stock.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.sql.Date;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {

    @Query(value ="SELECT A.BILL_NO, A.REF_ORDER_NO as orderNo, B.NAME as storeInfoName, " +
            "TO_CHAR(A.REGIST_DATE, 'YYYY-MM-DD') AS registDate " +
            "FROM TBL_BILL A " +
            "LEFT JOIN TBL_STORE_INFO B ON B.STORE_NO = A.REF_STORE_INFO_NO " +
            "WHERE A.REGIST_DATE BETWEEN :startDate AND :endDate " +
            "AND B.NAME LIKE '%'|| :store ||'%' ", nativeQuery = true)
    List<Tuple> billListSize(@Param("store")String store, @Param("startDate") Date startDate, @Param("endDate")Date endDate);

    @Query(value ="SELECT A.BILL_NO, A.REF_ORDER_NO as orderNo, B.NAME as storeInfoName, " +
            "TO_CHAR(A.REGIST_DATE, 'YYYY-MM-DD') AS registDate " +
            "FROM TBL_BILL A " +
            "LEFT JOIN TBL_STORE_INFO B ON B.STORE_NO = A.REF_STORE_INFO_NO " +
            "WHERE A.REGIST_DATE BETWEEN :startDate AND :endDate " +
            "AND B.NAME LIKE '%'|| :store ||'%' ", nativeQuery = true)
    Page<Tuple> billList(@Param("store")String store, @Param("startDate") Date startDate, @Param("endDate")Date endDate, Pageable paging);


    @Query(value ="SELECT A.BILL_NO, A.REF_ORDER_NO as orderNo, B.NAME as storeInfoName, " +
            "TO_CHAR(A.REGIST_DATE, 'YYYY-MM-DD') AS registDate " +
            "FROM TBL_BILL A " +
            "LEFT JOIN TBL_STORE_INFO B ON B.STORE_NO = A.REF_STORE_INFO_NO " +
            "WHERE A.REGIST_DATE BETWEEN :startDate AND :endDate " +
            "AND B.STORE_NO = :store ", nativeQuery = true)
    List<Tuple> myBillListSize(@Param("store")int store, @Param("startDate") Date startDate, @Param("endDate")Date endDate);

    @Query(value ="SELECT A.BILL_NO, A.REF_ORDER_NO as orderNo, B.NAME as storeInfoName, " +
            "TO_CHAR(A.REGIST_DATE, 'YYYY-MM-DD') AS registDate " +
            "FROM TBL_BILL A " +
            "LEFT JOIN TBL_STORE_INFO B ON B.STORE_NO = A.REF_STORE_INFO_NO " +
            "WHERE A.REGIST_DATE BETWEEN :startDate AND :endDate " +
            "AND B.STORE_NO = :store ", nativeQuery = true)
    Page<Tuple> myBillList(@Param("store")int store, @Param("startDate") Date startDate, @Param("endDate")Date endDate, Pageable paging);


}
