package com.css.autocsfinal.workstatus.repository;

import com.css.autocsfinal.workstatus.entity.EmployeeByWorkStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeByWorkStatusRepository extends JpaRepository<EmployeeByWorkStatus, Integer> {


}
