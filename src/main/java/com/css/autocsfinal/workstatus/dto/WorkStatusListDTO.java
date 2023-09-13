package com.css.autocsfinal.workstatus.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class WorkStatusListDTO {

    private int employeeNo;

    private int workStatusCode;

    private WorkStatusDTO workStatus;

    public WorkStatusListDTO(int employeeNo, int workStatusCode) {
        this.employeeNo = employeeNo;
        this.workStatusCode = workStatusCode;
    }

}
