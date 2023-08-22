package com.css.autocsfinal.member.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TBL_EMPLOYEE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeAndDepartmentAndPosition {

    @Id
    @Column(name = "EMPLOYEE_NO")
    private int employeeNo;

    @Column(name = "NAME")
    private String name;

    @Column(name = "JOIN_DATE")
    private Date employeeJoin;

    @ManyToOne(targetEntity = Department.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "REF_DEPARTMENT_CODE")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "REF_POSITION_CODE")
    private Position position;

}
