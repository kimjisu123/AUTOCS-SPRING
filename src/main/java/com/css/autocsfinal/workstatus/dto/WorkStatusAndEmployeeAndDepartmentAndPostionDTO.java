package com.css.autocsfinal.workstatus.dto;

import com.css.autocsfinal.workstatus.entity.WorkStatusList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkStatusAndEmployeeAndDepartmentAndPostionDTO {

    private int workStatusCode;

    private Date attendanceTime;

    private Date quittingTime;

    private char vacationStatus;

    private char absenceWorkStatus;

    private Date extensionTime;

    private List<WorkStatusList> workStatusLists;

    private int totalCount;

}
