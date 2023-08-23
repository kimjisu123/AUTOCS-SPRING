package com.css.autocsfinal.stock.repository;

import com.css.autocsfinal.stock.entity.Category;
import com.css.autocsfinal.stock.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
