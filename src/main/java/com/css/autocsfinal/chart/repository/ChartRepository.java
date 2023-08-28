package com.css.autocsfinal.chart.repository;

import com.css.autocsfinal.chart.entity.ChartEntity;
import com.css.autocsfinal.member.entity.EmployeeAndDepartmentAndPosition;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChartRepository extends JpaRepository<ChartEntity, Integer> {

    @EntityGraph(attributePaths = {"department", "position"})
    List<ChartEntity> findAll();
}
