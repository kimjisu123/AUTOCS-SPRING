package com.css.autocsfinal.workstatus.repository;

import com.css.autocsfinal.workstatus.entity.WorkStatus;
import com.css.autocsfinal.workstatus.entity.WorkStatusList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkStatusListRepository extends JpaRepository<WorkStatusList, Integer> {


    List<WorkStatusList> findByEmployeeNo(int employeeNo);

    List<WorkStatusList> findByEmployeeNoOrderByEmployeeNo(int employeeNo);

    List<WorkStatusList> findByEmployeeNoOrderByWorkStatusCode(int employeeNo);
}
