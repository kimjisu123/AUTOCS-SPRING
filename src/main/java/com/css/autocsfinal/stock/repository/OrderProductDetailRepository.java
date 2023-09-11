package com.css.autocsfinal.stock.repository;

import com.css.autocsfinal.stock.entity.OrderProduct;
import com.css.autocsfinal.stock.entity.OrderProductDetail;
import com.css.autocsfinal.stock.entity.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.List;

public interface OrderProductDetailRepository extends JpaRepository<OrderProductDetail, Integer> {

    OrderProductDetail findById(int myOrderProductNo);

}
