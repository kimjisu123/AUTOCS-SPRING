package com.css.autocsfinal.member.repository;

import com.css.autocsfinal.member.entity.EmployeeAndDepartmentAndPosition;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeAndDepartmentAndPositionRepository extends JpaRepository<EmployeeAndDepartmentAndPosition, Integer> {

    // 회원 번호로 직원 정보 조회
    @EntityGraph(attributePaths = {"member", "department", "position"})
    EmployeeAndDepartmentAndPosition findByMemberNo(int memberNo);

    //직원 전체 조회
    @EntityGraph(attributePaths = {"member", "department", "position"})
    List<EmployeeAndDepartmentAndPosition> findAll();
}
