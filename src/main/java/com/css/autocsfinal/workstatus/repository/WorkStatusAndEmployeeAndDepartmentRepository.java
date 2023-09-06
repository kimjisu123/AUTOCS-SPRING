package com.css.autocsfinal.workstatus.repository;

import com.css.autocsfinal.workstatus.dto.WorkStatusResult;
import com.css.autocsfinal.workstatus.entity.WorkStatusAndEmployeeAndDepartmentAndPostion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface WorkStatusAndEmployeeAndDepartmentRepository extends JpaRepository<WorkStatusAndEmployeeAndDepartmentAndPostion, Long> {

    @Query("SELECT w, l, e, d, p FROM WorkStatusAndEmployeeAndDepartmentAndPostion w " +
            "join w.workStatusLists l " +
            "join l.employee e " +
            "join e.department d " +
            "join e.position p " +
            "where d.name =:name")
    List<WorkStatusAndEmployeeAndDepartmentAndPostion> findByDepartmentName(@Param("name") String departmentName);

    @Query("SELECT new com.css.autocsfinal.workstatus.dto.WorkStatusResult(w, e.name, d.name, p.name) FROM WorkStatusAndEmployeeAndDepartmentAndPostion w " +
            "join w.workStatusLists l " +
            "join l.employee e " +
            "join e.department d " +
            "join e.position p ")
    List<WorkStatusResult> findAllWorkStatusResult();
}
