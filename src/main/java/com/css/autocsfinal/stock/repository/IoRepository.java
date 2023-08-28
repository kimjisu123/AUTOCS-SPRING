package com.css.autocsfinal.stock.repository;

import com.css.autocsfinal.stock.entity.Io;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IoRepository extends JpaRepository<Io, Integer> {
}
