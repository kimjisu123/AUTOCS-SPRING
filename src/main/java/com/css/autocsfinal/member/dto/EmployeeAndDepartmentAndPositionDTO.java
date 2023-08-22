package com.css.autocsfinal.member.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeAndDepartmentAndPositionDTO {

    private int employeeNo;
    private String name;
    private Date employeeJoin;

    private String department;
    private String position;

}
