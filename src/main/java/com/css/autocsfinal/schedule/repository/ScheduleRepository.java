package com.css.autocsfinal.schedule.repository;

import com.css.autocsfinal.member.entity.EmployeeAndDepartmentAndPosition;
import com.css.autocsfinal.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    List<Schedule> findByMemberNo(int memberNo);
}
