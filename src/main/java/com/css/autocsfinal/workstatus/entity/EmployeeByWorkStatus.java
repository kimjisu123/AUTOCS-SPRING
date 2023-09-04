package com.css.autocsfinal.workstatus.entity;

import com.css.autocsfinal.member.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class EmployeeByWorkStatus {

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

    @Column(name = "REF_POSITION_CODE")
    private String position;

    @Column(name = "UP_CODE")
    private String upCode;

    @Column(name = "REF_MEMBER_NO")
    private int memberNo;

    @ManyToOne
    @JoinColumn(name ="REF_DEPARTMENT_CODE")
    private Department department;

    @Override
    public String toString() {
        return "Employee{" +
                "employeeNo=" + employeeNo +
                ", name='" + name + '\'' +
                ", employeeJoin=" + employeeJoin +
                ", employeeOut=" + employeeOut +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeePhone='" + employeePhone + '\'' +
                ", upCode=" + upCode +
                ", position=" + position +
                ", memberNo=" + memberNo +
                '}';
    }
}