package com.css.autocsfinal.stock.repository;

import com.css.autocsfinal.stock.dto.IoSummaryDTO;
import com.css.autocsfinal.stock.entity.Io;
import com.css.autocsfinal.stock.entity.IoDetail;
import com.css.autocsfinal.stock.entity.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.sql.Date;
import java.util.List;

public interface IoDetailRepository extends JpaRepository<IoDetail, Integer> {
    Page<IoDetail> findAll(Pageable paging);

//    List<IoDetail> findByNameContaining(String y);
//    Page<IoDetail> findByNameContaining(String y, Pageable paging);
}
