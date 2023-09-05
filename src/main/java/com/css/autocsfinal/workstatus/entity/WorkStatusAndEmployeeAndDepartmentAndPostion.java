package com.css.autocsfinal.workstatus.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TBL_WORK_STATUS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkStatusAndEmployeeAndDepartmentAndPostion {

    @Id
    @Column(name = "WORK_STATUS_CODE")
    private int workStatusCode;

    @Column(name ="ATTENDANCE_TIME")
    private Date attendanceTime;

    @Column(name = "QUITTING_TIME")
    private Date quittingTime;

    @Column(name = "VACATION_STATUS")
    private char vacationStatus;

    @Column(name = "ABSENCE_WORK_STATUS")
    private char absenceWorkStatus;

    @Column(name = "EXTENSION_TIME")
    private Date extensionTime;

    @OneToMany(mappedBy = "workStatusCode")
    private List<WorkStatusList> workStatusLists;

}
