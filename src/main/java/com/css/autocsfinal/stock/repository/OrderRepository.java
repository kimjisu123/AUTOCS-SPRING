package com.css.autocsfinal.stock.repository;

import com.css.autocsfinal.stock.entity.Order;
import com.css.autocsfinal.stock.entity.OrderDetail;
import com.css.autocsfinal.stock.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.sql.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = "SELECT ORDER_NO " +
            "FROM TBL_ORDER ORDER BY ORDER_NO DESC FETCH FIRST 1 ROW ONLY", nativeQuery = true)
    int findLastOrderNo();

    List<Order> findByStatus(String y);
    Page<Order> findByStatus(String y, Pageable paging);

    @Query(value ="SELECT A.ORDER_NO, A.REF_BILL_NO, A.STORE_INFO_NO, " +
            "TO_CHAR(A.REGIST_DATE, 'YYYY-MM-DD') AS registDate, " +
            "A.STATUS " +
            "FROM TBL_ORDER A " +
            "WHERE A.STORE_INFO_NO = :store " +
            "AND A.REGIST_DATE BETWEEN :startDate AND :endDate " +
            "AND A.STATUS IN ('Y' , 'C') " +
            "ORDER BY A.ORDER_NO DESC", nativeQuery = true)
    List<Tuple> orderSize(@Param("store") String store, @Param("startDate") Date startDate, @Param("endDate")Date endDate);

    @Query(value ="SELECT A.ORDER_NO, A.REF_BILL_NO, A.STORE_INFO_NO, " +
            "TO_CHAR(A.REGIST_DATE, 'YYYY-MM-DD') AS registDate, " +
            "A.STATUS " +
            "FROM TBL_ORDER A " +
            "WHERE A.STORE_INFO_NO = :store " +
            "AND A.REGIST_DATE BETWEEN :startDate AND :endDate " +
            "AND A.STATUS IN ('Y' , 'C') " +
                "ORDER BY A.ORDER_NO DESC", nativeQuery = true)
    Page<Tuple> order(@Param("store") String store, @Param("startDate") Date startDate, @Param("endDate")Date endDate, Pageable paging);


}
