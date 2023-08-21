package com.css.autocsfinal.member.entity;

import com.css.autocsfinal.member.dto.DepartmentDTO;
import com.css.autocsfinal.member.dto.MemberDTO;
import com.css.autocsfinal.member.dto.PositionDTO;
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
public class EmployeeAndDepartmentAndPositionAndMember {

    @Id
    @Column(name = "EMPLOYEE_NO")
    private int employeeNo;

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

    @ManyToOne
    @JoinColumn(name = "REF_DEPARTMENT_CODE")
    private Department DepartmentCode;

    @ManyToOne
    @JoinColumn(name = "REF_POSITION_CODE")
    private Position PositionCode;

    @ManyToOne
    @JoinColumn(name = "REF_MEMBER_NO")
    private Member MemberNo;

}
