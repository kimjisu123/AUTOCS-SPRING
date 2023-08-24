package com.css.autocsfinal.stock.repository;

import com.css.autocsfinal.stock.entity.Product;
import com.css.autocsfinal.stock.entity.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
    Page<ProductDetail> findAll(Pageable paging);
}
