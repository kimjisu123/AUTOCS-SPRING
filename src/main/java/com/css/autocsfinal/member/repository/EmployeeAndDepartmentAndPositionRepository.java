package com.css.autocsfinal.member.repository;

import com.css.autocsfinal.member.entity.EmployeeAndDepartmentAndPosition;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeAndDepartmentAndPositionRepository extends JpaRepository<EmployeeAndDepartmentAndPosition, Integer> {
    @EntityGraph(attributePaths = {"member", "department", "position"})
    List<EmployeeAndDepartmentAndPosition> findAll();
}
