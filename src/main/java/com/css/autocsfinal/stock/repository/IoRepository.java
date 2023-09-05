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

    @Query(value = "SELECT a.REF_PRODUCT_NO, " +
            "CAST(SUM(CASE WHEN a.IO = 'IN' THEN a.totalQuantity ELSE 0 END) AS INTEGER) as totalQuantityIn, " +
            "CAST(SUM(CASE WHEN a.IO = 'OUT' THEN a.totalQuantity ELSE 0 END) AS INTEGER) as totalQuantityOut, " +
            "b.NAME as productName, c.NAME as categoryName, d.NAME as standardName , e.NAME as unitName, b.STOCK , b.PRICE, b.ETC " +
            "FROM (SELECT x.REF_PRODUCT_NO, x.IO, SUM(x.QUANTITY) as totalQuantity " +
            "FROM TBL_PRODUCT_IO x " +
            "LEFT JOIN TBL_PRODUCT z ON z.PRODUCT_NO = x.REF_PRODUCT_NO " +
            "WHERE z.NAME LIKE '%'|| :s ||'%' " +
//            "WHERE z.NAME LIKE '%%' "
            "AND x.REGIST_DATE BETWEEN  :startDate AND :endDate " +
            "GROUP BY x.REF_PRODUCT_NO, x.IO" +
            ") a " +
            "LEFT JOIN TBL_PRODUCT b ON b.PRODUCT_NO = a.REF_PRODUCT_NO " +
            "LEFT JOIN TBL_PRODUCT_CATEGORY c ON c.PRODUCT_CATEGORY_NO = b.REF_PRODUCT_CATEGORY_NO " +
            "LEFT JOIN TBL_PRODUCT_STANDARD d ON d.PRODUCT_STANDARD_NO = b.REF_PRODUCT_STANDARD_NO " +
            "LEFT JOIN TBL_PRODUCT_UNIT e ON e.PRODUCT_UNIT_NO = b.REF_PRODUCT_UNIT_NO " +
            "GROUP BY a.REF_PRODUCT_NO, b.NAME, c.NAME, d.NAME, e.NAME, b.STOCK, b.PRICE, b.ETC " +
            "ORDER BY a.REF_PRODUCT_NO", nativeQuery = true)
    List<Tuple> summarizeSize(@Param("s") String s, @Param("startDate")Date startDate, @Param("endDate")Date endDate);


    @Query(value = "SELECT a.REF_PRODUCT_NO, " +
            "CAST(SUM(CASE WHEN a.IO = 'IN' THEN a.totalQuantity ELSE 0 END) AS INTEGER) as totalQuantityIn, " +
            "CAST(SUM(CASE WHEN a.IO = 'OUT' THEN a.totalQuantity ELSE 0 END) AS INTEGER) as totalQuantityOut, " +
            "b.NAME as productName, c.NAME as categoryName, d.NAME as standardName , e.NAME as unitName, b.STOCK , b.PRICE, b.ETC " +
            "FROM (SELECT x.REF_PRODUCT_NO, x.IO, SUM(x.QUANTITY) as totalQuantity " +
            "FROM TBL_PRODUCT_IO x " +
            "LEFT JOIN TBL_PRODUCT z ON z.PRODUCT_NO = x.REF_PRODUCT_NO " +
            "WHERE z.NAME LIKE '%'|| :s ||'%' " +
            "AND x.REGIST_DATE BETWEEN  :startDate AND :endDate " +
            "GROUP BY x.REF_PRODUCT_NO, x.IO" +
            ") a " +
            "LEFT JOIN TBL_PRODUCT b ON b.PRODUCT_NO = a.REF_PRODUCT_NO " +
            "LEFT JOIN TBL_PRODUCT_CATEGORY c ON c.PRODUCT_CATEGORY_NO = b.REF_PRODUCT_CATEGORY_NO " +
            "LEFT JOIN TBL_PRODUCT_STANDARD d ON d.PRODUCT_STANDARD_NO = b.REF_PRODUCT_STANDARD_NO " +
            "LEFT JOIN TBL_PRODUCT_UNIT e ON e.PRODUCT_UNIT_NO = b.REF_PRODUCT_UNIT_NO " +
            "GROUP BY a.REF_PRODUCT_NO, b.NAME, c.NAME, d.NAME, e.NAME, b.STOCK, b.PRICE, b.ETC " +
            "ORDER BY a.REF_PRODUCT_NO", nativeQuery = true)
    Page<Tuple> summarize(@Param("s") String s, @Param("startDate")Date startDate, @Param("endDate")Date endDate, Pageable paging);


//        @Query(value = "SELECT a.REF_PRODUCT_NO, " +
//            "SUM(CASE WHEN a.IO = 'IN' THEN a.totalQuantity ELSE 0 END) as totalQuantityIn, " +
//            "SUM(CASE WHEN a.IO = 'OUT' THEN a.totalQuantity ELSE 0 END) as totalQuantityOut, " +
//            "b.NAME as productName, c.NAME as categoryName, d.NAME as standardName , e.NAME as unitName, b.STOCK , b.PRICE, b.ETC " +
//            "FROM (SELECT x.REF_PRODUCT_NO, x.IO, SUM(x.QUANTITY) as totalQuantity " +
//            "FROM TBL_PRODUCT_IO x " +
//            "LEFT JOIN TBL_PRODUCT z ON z.PRODUCT_NO = x.REF_PRODUCT_NO " +
////            "WHERE z.NAME LIKE '%'||#{s}||'%' " +
//            "WHERE z.NAME LIKE '%%' " +
//            "AND x.REGIST_DATE BETWEEN '2023-08-01' AND '2023-08-30'" +
//            "GROUP BY x.REF_PRODUCT_NO, x.IO" +
//            ") a " +
//            "LEFT JOIN TBL_PRODUCT b ON b.PRODUCT_NO = a.REF_PRODUCT_NO " +
//            "LEFT JOIN TBL_PRODUCT_CATEGORY c ON c.PRODUCT_CATEGORY_NO = b.REF_PRODUCT_CATEGORY_NO " +
//            "LEFT JOIN TBL_PRODUCT_STANDARD d ON d.PRODUCT_STANDARD_NO = b.REF_PRODUCT_STANDARD_NO " +
//            "LEFT JOIN TBL_PRODUCT_UNIT e ON e.PRODUCT_UNIT_NO = b.REF_PRODUCT_UNIT_NO " +
//            "GROUP BY a.REF_PRODUCT_NO, b.NAME, c.NAME, d.NAME, e.NAME, b.STOCK, b.PRICE, b.ETC " +
//            "ORDER BY a.REF_PRODUCT_NO", nativeQuery = true)
//    List<Tuple> summarizeSize(@Param("s") String s, @Param("startDate")Date startDate, @Param("endDate")Date endDate);
//
//
//    @Query(value = "SELECT a.REF_PRODUCT_NO, " +
//            "SUM(CASE WHEN a.IO = 'IN' THEN a.totalQuantity ELSE 0 END) as totalQuantityIn, " +
//            "SUM(CASE WHEN a.IO = 'OUT' THEN a.totalQuantity ELSE 0 END) as totalQuantityOut, " +
//            "b.NAME as productName, c.NAME as categoryName, d.NAME as standardName , e.NAME as unitName, b.STOCK , b.PRICE, b.ETC " +
//            "FROM (SELECT x.REF_PRODUCT_NO, x.IO, SUM(x.QUANTITY) as totalQuantity " +
//            "FROM TBL_PRODUCT_IO x " +
//            "LEFT JOIN TBL_PRODUCT z ON z.PRODUCT_NO = x.REF_PRODUCT_NO " +
//            "WHERE z.NAME LIKE '%%' " +
//            "AND x.REGIST_DATE BETWEEN '2023-08-01' AND '2023-08-30'" +
//            "GROUP BY x.REF_PRODUCT_NO, x.IO" +
//            ") a " +
//            "LEFT JOIN TBL_PRODUCT b ON b.PRODUCT_NO = a.REF_PRODUCT_NO " +
//            "LEFT JOIN TBL_PRODUCT_CATEGORY c ON c.PRODUCT_CATEGORY_NO = b.REF_PRODUCT_CATEGORY_NO " +
//            "LEFT JOIN TBL_PRODUCT_STANDARD d ON d.PRODUCT_STANDARD_NO = b.REF_PRODUCT_STANDARD_NO " +
//            "LEFT JOIN TBL_PRODUCT_UNIT e ON e.PRODUCT_UNIT_NO = b.REF_PRODUCT_UNIT_NO " +
//            "GROUP BY a.REF_PRODUCT_NO, b.NAME, c.NAME, d.NAME, e.NAME, b.STOCK, b.PRICE, b.ETC " +
//            "ORDER BY a.REF_PRODUCT_NO", nativeQuery = true)
//    Page<Tuple> summarize(@Param("s") String s, @Param("startDate")Date startDate, @Param("endDate")Date endDate, Pageable paging);



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

//    @Query("SELECT a.refProductNo, " +
//            "SUM(CASE WHEN a.io = 'IN' THEN a.totalQuantity ELSE 0 END) as totalQuantityIn, " +
//            "SUM(CASE WHEN a.io = 'OUT' THEN a.totalQuantity ELSE 0 END) as totalQuantityOut, " +
//            "b.name as productName, c.name as categoryName, d.name as standardName , e.name as unitName, b.stock , b.price, b.etc " +
//            "FROM (SELECT x.refProductNo, x.io, SUM(x.quantity) as totalQuantity " +
//            "FROM Io x " +
//            "LEFT JOIN Product z ON z.productNo = x.refProductNo " +
//            "WHERE z.name LIKE CONCAT('%', :y, '%') " +
//            "AND x.registDate >= :startDate AND x.registDate <= :endDate " +
//            "GROUP BY x.refProductNo, x.io) a " +
//            "LEFT JOIN Product b ON b.productNo = a.refProductNo " +
//            "LEFT JOIN Category c ON c.productCategoryNo = b.refProductCategoryNo " +
//            "LEFT JOIN Standard d ON d.productStandardNo = b.refProductStandardNo " +
//            "LEFT JOIN Unit e ON e.productUnitNo = b.refProductUnitNo " +
//            "GROUP BY a.refProductNo, b.name, c.name, d.name, e.name, b.stock, b.price, b.etc " +
//            "ORDER BY a.refProductNo"
//    )

//    @Query("SELECT a.refProductNo, " +
//            "SUM(CASE WHEN a.io = 'IN' THEN a.totalQuantity ELSE 0 END) as totalQuantityIn, " +
//            "SUM(CASE WHEN a.io = 'OUT' THEN a.totalQuantity ELSE 0 END) as totalQuantityOut, " +
//            "b.name as productName, c.name as categoryName, d.name as standardName , e.name as unitName, b.stock , b.price, b.etc " +
//            "FROM (SELECT x.refProductNo, x.io, SUM(x.quantity) as totalQuantity " +
//            "FROM Io x " +
//            "LEFT JOIN Product z ON z.productNo = x.refProductNo " +
//            "WHERE z.name LIKE %:y% " +
//            "AND x.registDate BETWEEN :startDate AND :endDate " +
//            "GROUP BY x.refProductNo, x.io" +
//            ") a " +
//            "LEFT JOIN Product b ON b.productNo = a.refProductNo " +
//            "LEFT JOIN Category c ON c.productCategoryNo = b.refProductCategoryNo " +
//            "LEFT JOIN Standard d ON d.productStandardNo = b.refProductStandardNo " +
//            "LEFT JOIN Unit e ON e.productUnitNo = b.refProductUnitNo " +
//            "GROUP BY a.refProductNo, b.name, c.name, d.name, e.name, b.stock, b.price, b.etc " +
//            "ORDER BY a.refProductNo"
//    )
}

