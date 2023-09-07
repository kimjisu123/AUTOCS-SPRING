package com.css.autocsfinal.stock.repository;

import com.css.autocsfinal.stock.entity.Order;
import com.css.autocsfinal.stock.entity.OrderDetail;
import com.css.autocsfinal.stock.entity.OrderProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    List<OrderDetail> findByStatus(String y);
    Page<OrderDetail> findByStatus(String y, Pageable paging);
}
