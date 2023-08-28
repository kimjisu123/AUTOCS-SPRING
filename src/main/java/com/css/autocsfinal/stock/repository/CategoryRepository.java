package com.css.autocsfinal.stock.repository;

import com.css.autocsfinal.stock.entity.Category;
import com.css.autocsfinal.stock.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Page<Category> findAll(Pageable paging);
    List<Category> findByUseYnOrderByName(String y);

}
