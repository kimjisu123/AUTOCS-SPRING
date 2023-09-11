package com.css.autocsfinal.member.repository;

import com.css.autocsfinal.member.dto.EmployeeDTO;
import com.css.autocsfinal.member.entity.Department;
import com.css.autocsfinal.member.entity.Employee;
import com.css.autocsfinal.member.entity.Position;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e WHERE e.departmentCode = :name")
    List<Employee> findByRefDepartmentCode(@Param("name") String departmentName);
    Employee findByEmployeeNo(int employeeNo);


}
