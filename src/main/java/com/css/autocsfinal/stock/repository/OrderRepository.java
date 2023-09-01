package com.css.autocsfinal.stock.repository;

import com.css.autocsfinal.stock.entity.Order;
import com.css.autocsfinal.stock.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = "SELECT ORDER_NO " +
            "FROM TBL_ORDER ORDER BY ORDER_NO DESC FETCH FIRST 1 ROW ONLY", nativeQuery = true)
    int findLastOrderNo();

}
