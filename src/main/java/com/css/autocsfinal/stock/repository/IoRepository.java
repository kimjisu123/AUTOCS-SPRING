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

    @Query("SELECT a.refProductNo, " +
            "SUM(CASE WHEN a.io = 'IN' THEN a.totalQuantity ELSE 0 END) as totalQuantityIn, " +
            "SUM(CASE WHEN a.io = 'OUT' THEN a.totalQuantity ELSE 0 END) as totalQuantityOut, " +
            "b.name as productName, c.name as categoryName, d.name as standardName , e.name as unitName, b.stock , b.price, b.etc " +
            "FROM (SELECT x.refProductNo, x.io, SUM(x.quantity) as totalQuantity " +
            "FROM Io x " +
            "LEFT JOIN Product z ON z.productNo = x.refProductNo " +
            "WHERE z.name LIKE CONCAT('%', :y, '%') " +
            "AND x.registDate >= :startDate AND x.registDate <= :endDate " +
            "GROUP BY x.refProductNo, x.io) a " +
            "LEFT JOIN Product b ON b.productNo = a.refProductNo " +
            "LEFT JOIN Category c ON c.productCategoryNo = b.refProductCategoryNo " +
            "LEFT JOIN Standard d ON d.productStandardNo = b.refProductStandardNo " +
            "LEFT JOIN Unit e ON e.productUnitNo = b.refProductUnitNo " +
            "GROUP BY a.refProductNo, b.name, c.name, d.name, e.name, b.stock, b.price, b.etc " +
            "ORDER BY a.refProductNo"
    )
    List<Tuple> summarizeSize(@Param("y") String y, @Param("startDate")Date startDate, @Param("endDate")Date endDate);


    @Query("SELECT a.refProductNo, " +
            "SUM(CASE WHEN a.io = 'IN' THEN a.totalQuantity ELSE 0 END) as totalQuantityIn, " +
            "SUM(CASE WHEN a.io = 'OUT' THEN a.totalQuantity ELSE 0 END) as totalQuantityOut, " +
            "b.name as productName, c.name as categoryName, d.name as standardName , e.name as unitName, b.stock , b.price, b.etc " +
            "FROM (SELECT x.refProductNo, x.io, SUM(x.quantity) as totalQuantity " +
            "FROM Io x " +
            "LEFT JOIN Product z ON z.productNo = x.refProductNo " +
            "WHERE z.name LIKE CONCAT('%', :y, '%') " +
            "AND x.registDate >= :startDate AND x.registDate <= :endDate " +
            "GROUP BY x.refProductNo, x.io) a " +
            "LEFT JOIN Product b ON b.productNo = a.refProductNo " +
            "LEFT JOIN Category c ON c.productCategoryNo = b.refProductCategoryNo " +
            "LEFT JOIN Standard d ON d.productStandardNo = b.refProductStandardNo " +
            "LEFT JOIN Unit e ON e.productUnitNo = b.refProductUnitNo " +
            "GROUP BY a.refProductNo, b.name, c.name, d.name, e.name, b.stock, b.price, b.etc " +
            "ORDER BY a.refProductNo"
    )
    Page<Tuple> summarize(@Param("y") String y, @Param("startDate")Date startDate, @Param("endDate")Date endDate, Pageable paging);



//    @Query("SELECT a.refProductNo, a.io, SUM(a.quantity) as totalQuantity, " +
//            "b.name as productName, c.name as categoryName, d.name as standardName , e.name as unitName, b.stock , b.price, b.etc " +
//            "FROM Io a " +
//            "LEFT JOIN Product b ON b.productNo = a.refProductNo " +
//            "LEFT JOIN Category c ON c.productCategoryNo = b.refProductCategoryNo " +
//            "LEFT JOIN Standard d ON d.productStandardNo = b.refProductStandardNo " +
//            "LEFT JOIN Unit e ON e.productUnitNo = b.refProductUnitNo " +
//            "WHERE b.name LIKE %:y% " +
//            "AND a.registDate BETWEEN :startDate AND :endDate " +
//            "GROUP BY a.refProductNo, a.io, b.name, c.name, d.name, e.name, b.stock, b.price, b.etc " +
//            "ORDER BY a.refProductNo"
//    )
}

