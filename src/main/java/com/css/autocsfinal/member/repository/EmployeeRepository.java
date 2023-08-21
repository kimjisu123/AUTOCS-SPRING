package com.css.autocsfinal.member.repository;

import com.css.autocsfinal.member.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
