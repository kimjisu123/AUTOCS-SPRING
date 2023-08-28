package com.css.autocsfinal.chart.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TBL_EMPLOYEE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeAndPositionEntity {

    @Id
    @Column(name = "EMPLOYEE_NO")
    private String employeeNo;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "REF_POSITION_CODE")
    private PositionEntity position;

    @Column(name = "REF_DEPARTMENT_CODE")
    private String departmentCode;
}
