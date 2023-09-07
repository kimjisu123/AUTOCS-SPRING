package com.css.autocsfinal.workstatus.repository;

import com.css.autocsfinal.workstatus.entity.EmployeeAndWorkStatus;
import com.css.autocsfinal.workstatus.entity.EmployeeByWorkStatus;
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
            "LEFT JOIN l.workStatus w ")
    List<EmployeeAndWorkStatus> findByOrderByName();

}
