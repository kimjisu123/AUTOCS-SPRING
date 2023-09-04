package com.css.autocsfinal.stock.repository;

import com.css.autocsfinal.stock.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {

}
