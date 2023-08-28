package com.css.autocsfinal.chart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeAndPositionDTO {

    private String employeeNo;
    private String name;
    private String refPositionCode;
    private String refDepartmentCode;
}
