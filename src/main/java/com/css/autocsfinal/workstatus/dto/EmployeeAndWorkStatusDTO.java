package com.css.autocsfinal.workstatus.dto;

import com.css.autocsfinal.member.entity.Department;
import com.css.autocsfinal.member.entity.Position;
import com.css.autocsfinal.workstatus.entity.WorkStatusList2;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeAndWorkStatusDTO {

    private int employeeNo;

    private String name;

    private Date employeeJoin;

    private Date employeeOut;

    private String employeeEmail;

    private String employeePhone;

    private String upCode;

    private int memberNo;

    private Department department;

    private Position position;

    private List<WorkStatusList2> workStatusLists;

    public void setDate(Date date) {
    }
}