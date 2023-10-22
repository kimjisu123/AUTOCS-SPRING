package com.css.autocsfinal.workstatus.repository;

import com.css.autocsfinal.workstatus.entity.WorkStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkStatusRepsitory extends JpaRepository<WorkStatus, Long> {

    Optional<WorkStatus> findTopByOrderByAttendanceTimeDesc();

    WorkStatus findByWorkStatusCode(int temp);
}