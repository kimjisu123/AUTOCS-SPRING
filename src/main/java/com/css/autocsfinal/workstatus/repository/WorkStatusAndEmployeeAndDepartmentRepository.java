package com.css.autocsfinal.workstatus.repository;

import com.css.autocsfinal.workstatus.entity.WorkStatus;
import com.css.autocsfinal.workstatus.entity.WorkStatusAndEmployeeAndDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkStatusAndEmployeeAndDepartmentRepository extends JpaRepository<WorkStatusAndEmployeeAndDepartment, Long> {

    @Query("SELECT w, l, e, d FROM WorkStatusAndEmployeeAndDepartment w join w.workStatusLists l join l.employee e join e.department d where d.name =:name")
    List<WorkStatusAndEmployeeAndDepartment> findByDepartmentName(@Param("name") String departmentName);
}
