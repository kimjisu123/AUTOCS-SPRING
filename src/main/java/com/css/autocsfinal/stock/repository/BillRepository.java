package com.css.autocsfinal.stock.repository;

import com.css.autocsfinal.stock.entity.Bill;
import com.css.autocsfinal.stock.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {

}
