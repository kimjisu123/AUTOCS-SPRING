package com.css.autocsfinal.workstatus.repository;

import com.css.autocsfinal.stock.entity.Category;
import com.css.autocsfinal.workstatus.entity.EmployeeAndWorkStatus;
import com.css.autocsfinal.workstatus.entity.EmployeeByWorkStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface EmployeeAndWorkStatusRepository extends JpaRepository<EmployeeAndWorkStatus, Integer> {
    @Query("SELECT e, d, l, w FROM EmployeeAndWorkStatus e " +
            "LEFT JOIN e.department d " +
            "LEFT JOIN e.position p " +
            "LEFT JOIN e.workStatusLists l " +
            "LEFT JOIN l.workStatus w " +
            "ORDER BY e.name")
    List<EmployeeAndWorkStatus> findByOrderByName();

    @Query("SELECT DISTINCT e, d, l, w FROM EmployeeAndWorkStatus e " +
            "LEFT JOIN e.department d " +
            "LEFT JOIN e.position p " +
            "LEFT JOIN e.workStatusLists l " +
            "LEFT JOIN l.workStatus w " +
            "ORDER BY e.name")
    List<EmployeeAndWorkStatus> findByOrderByName(Pageable paging);

}
