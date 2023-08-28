package com.css.autocsfinal.stock.repository;

import com.css.autocsfinal.stock.entity.Io;
import com.css.autocsfinal.stock.entity.IoDetail;
import com.css.autocsfinal.stock.entity.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface IoDetailRepository extends JpaRepository<IoDetail, Integer> {
    Page<IoDetail> findAll(Pageable paging);

    @Query(value = "SELECT REF_PRODUCT_NO, IO, SUM(QUANTITY) AS TOTAL_QUANTITY " +
            "FROM TBL_PRODUCT_IO " +
            "WHERE REGIST_DATE BETWEEN '2023-08-01' AND '2023-08-29' " +
            "GROUP BY REF_PRODUCT_NO, IO " +
            "ORDER BY REF_PRODUCT_NO", nativeQuery = true)
    List<Object[]> summarizeIoBetweenRegistDate();

//    List<IoDetail> findByNameContaining(String y);
//    Page<IoDetail> findByNameContaining(String y, Pageable paging);
}
