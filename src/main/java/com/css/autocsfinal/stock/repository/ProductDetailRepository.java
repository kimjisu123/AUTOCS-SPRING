package com.css.autocsfinal.stock.repository;

import com.css.autocsfinal.stock.entity.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
    Page<ProductDetail> findAll(Pageable paging);
    List<ProductDetail> findByNameContaining(String y);
    Page<ProductDetail> findByNameContaining(String y, Pageable paging);
    Page<ProductDetail> findByNameContainingAndStatus(String name, String useYn, Pageable pageable);
}
