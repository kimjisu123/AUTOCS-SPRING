package com.css.autocsfinal.stock.repository;

import com.css.autocsfinal.stock.entity.Bill;
import com.css.autocsfinal.stock.entity.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillDetailRepository extends JpaRepository<BillDetail, Integer> {

}
