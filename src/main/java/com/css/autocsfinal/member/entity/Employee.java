package com.css.autocsfinal.member.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TBL_EMPLOYEE")
@SequenceGenerator(
        name = "EMPLOYEE_SEQ_GENERATOR",
        sequenceName = "SEQ_EMPLOYEE_NO",
        initialValue = 1, allocationSize = 1
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @Column(name = "EMPLOYEE_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "EMPLOYEE_SEQ_GENERATOR"
    )
    private int employeeNo;

    @Column(name = "NAME")
    private String name;

    @Column(name = "JOIN_DATE")
    private Date employeeJoin;

    @Column(name = "LEAVE_DATE")
    private Date employeeOut;

    @Column(name = "EMAIL")
    private String employeeEmail;

    @Column(name = "PHOME")
    private String employeePhone;

    @Column(name = "REF_DEPARTMENT_CODE")
    private String departmentCode;

    @Column(name = "REF_POSITION_CODE")
    private String position;

    @Column(name = "UP_CODE")
    private String upCode;

    @Column(name = "REF_MEMBER_NO")
    private int memberNo;

//    @Column(name="REF_M_FILE_NO")
//    private int memberFileNo;

    @Column(name = "ANNUAL")
    private int annual;

    @Override
    public String toString() {
        return "Employee{" +
                "employeeNo=" + employeeNo +
                ", name='" + name + '\'' +
                ", employeeJoin=" + employeeJoin +
                ", employeeOut=" + employeeOut +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeePhone='" + employeePhone + '\'' +
                ", departmentCode='" + departmentCode + '\'' +
                ", position='" + position + '\'' +
                ", upCode='" + upCode + '\'' +
                ", memberNo=" + memberNo +
                ", annual=" + annual +
                '}';
    }
}