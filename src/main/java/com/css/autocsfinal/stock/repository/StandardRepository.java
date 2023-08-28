package com.css.autocsfinal.stock.repository;

import com.css.autocsfinal.stock.entity.Standard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StandardRepository extends JpaRepository<Standard, Integer> {
    List<Standard> findByUseYnOrderByName(String y);
}
