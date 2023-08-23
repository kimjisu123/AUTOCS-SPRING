package com.css.autocsfinal.member.repository;

import com.css.autocsfinal.member.entity.EmployeeAndDepartmentAndPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeAndDepartmentAndPositionRepository extends JpaRepository<EmployeeAndDepartmentAndPosition, Integer> {
//    @Query("SELECT e FROM EmployeeAndDepartmentAndPosition e " +
//            "LEFT JOIN e.department d " +
//            "LEFT JOIN e.position p")
//    List<EmployeeAndDepartmentAndPosition> getJoinEmployee();
}
