package com.css.autocsfinal.workstatus.repository;

import com.css.autocsfinal.workstatus.entity.WorkStatus;
import com.css.autocsfinal.workstatus.entity.WorkStatusList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface WorkStatusListRepository extends JpaRepository<WorkStatusList, Integer> {



    List<WorkStatusList> findByEmployeeNoOrderByEmployeeNo(int employeeNo);

    List<WorkStatusList> findByEmployeeNoOrderByWorkStatusCode(int employeeNo);


    List<WorkStatusList> findByEmployeeNo(int employeeNo);

    @Query("select count(w) from WorkStatusList l " +
            "join l.workStatus w " +
            "where l.employeeNo = :employeeNo " +
            "and TO_CHAR(w.attendanceTime, 'YYYYMMDD') = TO_CHAR(current_date, 'YYYYMMDD') ")
    int countByAttendanceTime(int employeeNo);

    @Query("select count(w) from WorkStatusList l " +
            "join l.workStatus w " +
            "where l.employeeNo = :employeeNo " +
            "and TO_CHAR(w.quittingTime, 'YYYYMMDD') = TO_CHAR(current_date, 'YYYYMMDD') ")
    int countByquittingTime(int employeeNo);
}
