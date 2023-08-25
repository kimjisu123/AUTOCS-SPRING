package com.css.autocsfinal.chart.repository;

import com.css.autocsfinal.chart.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.jar.Attributes;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, String> {

    @EntityGraph(attributePaths = {"employee" ,"employee.position"})
    List<DepartmentEntity> findAll();
}
