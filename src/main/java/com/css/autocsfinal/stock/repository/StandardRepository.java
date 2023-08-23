package com.css.autocsfinal.stock.repository;

import com.css.autocsfinal.stock.entity.Product;
import com.css.autocsfinal.stock.entity.Standard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandardRepository extends JpaRepository<Standard, Integer> {
}
