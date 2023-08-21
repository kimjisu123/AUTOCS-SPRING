package com.css.autocsfinal.member.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeAndDepartmentAndPositionAndMemberDTO {

    private int employeeNo;
    private Date employeeJoin;
    private Date employeeOut;
    private String employeeEmail;
    private String employeePhone;
    private int employeeManager;

    private DepartmentDTO DepartmentCode;
    private PositionDTO PositionCode;
    private MemberDTO MemberNo;

}
