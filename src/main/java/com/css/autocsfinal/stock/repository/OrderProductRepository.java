package com.css.autocsfinal.stock.repository;

import com.css.autocsfinal.stock.entity.OrderProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.sql.Date;
import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {

    @Query(value = "SELECT B.ORDER_NO, C.NAME, E.NAME, D.NAME, F.NAME, G.NAME, D.PRICE, A.QUANTITY, D.ETC, A.REGIST_DATE, A.STATUS " +
            "FROM TBL_ORDER_PRODUCT A " +
            "LEFT JOIN TBL_ORDER B ON B.ORDER_NO = A.REF_ORDER_NO " +
            "LEFT JOIN TBL_STORE_INFO C ON C.STORE_NO = B.STORE_INFO_NO " +
            "LEFT JOIN TBL_PRODUCT D ON D.PRODUCT_NO = A.REF_PRODUCT_NO " +
            "LEFT JOIN TBL_PRODUCT_CATEGORY E ON E.PRODUCT_CATEGORY_NO = D.REF_PRODUCT_CATEGORY_NO " +
            "LEFT JOIN TBL_PRODUCT_UNIT F ON F.PRODUCT_UNIT_NO = D.REF_PRODUCT_UNIT_NO " +
            "LEFT JOIN TBL_PRODUCT_STANDARD G ON G.PRODUCT_STANDARD_NO = D.REF_PRODUCT_STANDARD_NO " +
            "WHERE A.STATUS = :status " +
            "AND C.NAME LIKE '%'|| :search ||'%' " +
            "AND A.REGIST_DATE BETWEEN :startDate AND :endDate " +
            "ORDER BY B.ORDER_NO DESC", nativeQuery = true)
    List<Tuple> orderListSize(@Param("status") String status, @Param("search")String search, @Param("startDate") Date startDate, @Param("endDate")Date endDate);

    @Query(value = "SELECT B.ORDER_NO, C.NAME, E.NAME, D.NAME, F.NAME, G.NAME, D.PRICE, A.QUANTITY, D.ETC, A.REGIST_DATE, A.STATUS " +
            "FROM TBL_ORDER_PRODUCT A " +
            "LEFT JOIN TBL_ORDER B ON B.ORDER_NO = A.REF_ORDER_NO " +
            "LEFT JOIN TBL_STORE_INFO C ON C.STORE_NO = B.STORE_INFO_NO " +
            "LEFT JOIN TBL_PRODUCT D ON D.PRODUCT_NO = A.REF_PRODUCT_NO " +
            "LEFT JOIN TBL_PRODUCT_CATEGORY E ON E.PRODUCT_CATEGORY_NO = D.REF_PRODUCT_CATEGORY_NO " +
            "LEFT JOIN TBL_PRODUCT_UNIT F ON F.PRODUCT_UNIT_NO = D.REF_PRODUCT_UNIT_NO " +
            "LEFT JOIN TBL_PRODUCT_STANDARD G ON G.PRODUCT_STANDARD_NO = D.REF_PRODUCT_STANDARD_NO " +
            "WHERE A.STATUS = :status " +
            "AND C.NAME LIKE '%'|| :search ||'%' " +
            "AND A.REGIST_DATE BETWEEN :startDate AND :endDate " +
            "ORDER BY B.ORDER_NO DESC", nativeQuery = true)
    Page<Tuple> orderList(@Param("status") String status, @Param("search")String search, @Param("startDate") Date startDate, @Param("endDate")Date endDate, Pageable paging);


}
