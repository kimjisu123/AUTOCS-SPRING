package com.css.autocsfinal.stock.repository;

import com.css.autocsfinal.stock.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnitRepository extends JpaRepository<Unit, Integer> {
    List<Unit> findByUseYnOrderByName(String y);
}
