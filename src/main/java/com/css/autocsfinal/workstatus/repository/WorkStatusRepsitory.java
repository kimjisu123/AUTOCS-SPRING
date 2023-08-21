package com.css.autocsfinal.workstatus.repository;

import com.css.autocsfinal.workstatus.entity.WorkStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkStatusRepsitory extends JpaRepository<WorkStatus, Long> {

}