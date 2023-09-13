package com.css.autocsfinal.workstatus.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class WorkStatusListPk implements Serializable {

    @Column(name = "EMPLOYEE_NO")
    private int employeeNo;

    @Column(name = "WORK_STATUS_CODE")
    private int workStatusCode;

}
