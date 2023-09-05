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
public class EmployeeAndDepartmentAndPosition {

    @Id
    @Column(name = "EMPLOYEE_NO")
    private int employeeNo;

    @Column(name = "EMAIL")
    private String employeeEmail;

    @Column(name = "JOIN_DATE")
    private Date employeeJoin;

    @Column(name = "LEAVE_DATE")
    private Date employeeOut;

    @Column(name = "PHOME")
    private String employeePhone;

    @Column(name = "NAME")
    private String name;

    @Column(name = "REASON")
    private String reason;

    @Column(name = "ANNUAL")
    private int annual;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REF_MEMBER_NO")
    private Member member;

    @ManyToOne()
    @JoinColumn(name = "REF_DEPARTMENT_CODE")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REF_POSITION_CODE")
    private Position position;

    @Override
    public String toString() {
        return "EmployeeAndDepartmentAndPosition{" +
                "employeeNo=" + employeeNo +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeeJoin=" + employeeJoin +
                ", employeeOut=" + employeeOut +
                ", employeePhone='" + employeePhone + '\'' +
                ", name='" + name + '\'' +
                ", member='" + member + '\'' +
                ", department=" + department +
                ", position=" + position +
                '}';
    }
}
