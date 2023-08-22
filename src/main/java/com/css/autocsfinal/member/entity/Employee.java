package com.css.autocsfinal.member.entity;

import com.css.autocsfinal.member.dto.DepartmentDTO;
import com.css.autocsfinal.member.dto.MemberDTO;
import com.css.autocsfinal.member.dto.PositionDTO;
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
@ToString
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

    @Column(name = "MANAGER_NO")
    private int employeeManager;

    @Column(name = "REF_DEPARTMENT_CODE")
    private String departmentCode;

    @Column(name = "REF_POSITION_CODE")
    private String positionCode;

}