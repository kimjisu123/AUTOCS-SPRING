package com.css.autocsfinal.mypage.repository;


import com.css.autocsfinal.mypage.entity.MemberAndEmployeeAndDepartmentAndPositionAndMemberFile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberAndEmployeeAndDepartmentAndPositionAndMemberFileRepository extends JpaRepository<MemberAndEmployeeAndDepartmentAndPositionAndMemberFile, Integer> {

    // 회원 번호로 직원 정보 조회
    @EntityGraph(attributePaths = {"member", "department", "position", "memberFile"})
    MemberAndEmployeeAndDepartmentAndPositionAndMemberFile findByMemberNo(int memberNo);

    //직원 전체 조회
    @EntityGraph(attributePaths = {"member", "department", "position", "memberFile"})
    List<MemberAndEmployeeAndDepartmentAndPositionAndMemberFile> findAll();

    //아이디 찾기
    @EntityGraph(attributePaths = {"member", "department", "position", "memberFile"})
    MemberAndEmployeeAndDepartmentAndPositionAndMemberFile findByNameAndEmployeeEmail(String name, String employeeEmail);
}
