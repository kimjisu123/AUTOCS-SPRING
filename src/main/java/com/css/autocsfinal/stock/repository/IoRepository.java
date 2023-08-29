package com.css.autocsfinal.stock.repository;

import com.css.autocsfinal.stock.dto.IoSummaryDTO;
import com.css.autocsfinal.stock.entity.Io;
import com.css.autocsfinal.stock.entity.IoDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.sql.Date;
import java.util.List;

public interface IoRepository extends JpaRepository<Io, Integer> {

    @Query("SELECT a.refProductNo, a.io, SUM(a.quantity) as totalQuantity, " +
            "b.name as productName, c.name as categoryName, d.name as standardName , e.name as unitName, b.stock , b.price, b.etc " +
            "FROM Io a " +
            "LEFT JOIN Product b ON b.productNo = a.refProductNo " +
            "LEFT JOIN Category c ON c.productCategoryNo = b.refProductCategoryNo " +
            "LEFT JOIN Standard d ON d.productStandardNo = b.refProductStandardNo " +
            "LEFT JOIN Unit e ON e.productUnitNo = b.refProductUnitNo " +
            "WHERE b.name LIKE %:y% " +
            "AND a.registDate BETWEEN :startDate AND :endDate " +
            "GROUP BY a.refProductNo, a.io, b.name, c.name, d.name, e.name, b.stock, b.price, b.etc " +
            "ORDER BY a.refProductNo"
    )
    List<Tuple> summarizeSize(@Param("y") String y, @Param("startDate")Date startDate, @Param("endDate")Date endDate);


    @Query("SELECT a.refProductNo, a.io, SUM(a.quantity) as totalQuantity, " +
            "b.name as productName, c.name as categoryName, d.name as standardName , e.name as unitName, b.stock , b.price, b.etc " +
            "FROM Io a " +
            "LEFT JOIN Product b ON b.productNo = a.refProductNo " +
            "LEFT JOIN Category c ON c.productCategoryNo = b.refProductCategoryNo " +
            "LEFT JOIN Standard d ON d.productStandardNo = b.refProductStandardNo " +
            "LEFT JOIN Unit e ON e.productUnitNo = b.refProductUnitNo " +
//            "WHERE a.registDate BETWEEN 20230801 AND 20230829 " +
            "WHERE b.name LIKE %:y% " +
            "AND a.registDate BETWEEN :startDate AND :endDate " +
            "GROUP BY a.refProductNo, a.io, b.name, c.name, d.name, e.name, b.stock, b.price, b.etc " +
            "ORDER BY a.refProductNo"
    )
    Page<Tuple> summarize(@Param("y") String y, @Param("startDate")Date startDate, @Param("endDate")Date endDate, Pageable paging);


}

