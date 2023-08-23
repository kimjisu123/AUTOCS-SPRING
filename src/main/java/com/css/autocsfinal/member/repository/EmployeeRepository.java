package com.css.autocsfinal.member.repository;

import com.css.autocsfinal.member.dto.EmployeeDTO;
import com.css.autocsfinal.member.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByName(String form);
}
