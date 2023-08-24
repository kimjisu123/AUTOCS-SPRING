package com.css.autocsfinal.member.repository;

import com.css.autocsfinal.member.entity.EmployeeAndDepartmentAndPosition;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeAndDepartmentAndPositionRepository extends JpaRepository<EmployeeAndDepartmentAndPosition, Integer> {
    @EntityGraph(attributePaths = {"member", "department", "position"})
    List<EmployeeAndDepartmentAndPosition> findAll();

    // 회원 번호로 직원 정보 조회
    @EntityGraph(attributePaths = {"member", "department", "position"})
    EmployeeAndDepartmentAndPosition findByMemberNo(int memberNo);
}
