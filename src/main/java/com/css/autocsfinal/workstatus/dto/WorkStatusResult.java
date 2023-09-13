package com.css.autocsfinal.workstatus.dto;

import com.css.autocsfinal.workstatus.entity.WorkStatusAndEmployeeAndDepartmentAndPostion;
import com.css.autocsfinal.workstatus.repository.WorkStatusRepsitory;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class WorkStatusResult {
    private int workStatusCode;

    private Date attendanceTime;

    private Date quittingTime;

    private char vactionStatus;

    private char absenceWorkStatus;

    private String empName;
    private String deptName;
    private String positionName;
    private int totalCount;

    public WorkStatusResult(WorkStatusAndEmployeeAndDepartmentAndPostion work, String empName, String deptName, String positionName) {
        this.workStatusCode = work.getWorkStatusCode();
        this.attendanceTime = work.getAttendanceTime();
        this.quittingTime = work.getQuittingTime();
        this.vactionStatus = work.getVacationStatus();
        this.absenceWorkStatus = work.getAbsenceWorkStatus();
        this.empName = empName;
        this.deptName = deptName;
        this.positionName = positionName;
    }
}
