package com.css.autocsfinal.member.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDTO {

    private int employeeNo;
    private String name;
    private Date employeeJoin;
    private Date employeeOut;
    private String employeeEmail;
    private String employeePhone;
    private int employeeManager;
    private String departmentCode;
    private int memberNo;
    private String positionCode;
    private String upCode;


}
