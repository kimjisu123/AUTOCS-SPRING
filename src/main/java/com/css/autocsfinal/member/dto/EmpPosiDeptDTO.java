package com.css.autocsfinal.member.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmpPosiDeptDTO {

    private int employeeNo;
    private String name;
    private int annual;
    private PositionDTO position;
    private DepartmentDTO department;
}
