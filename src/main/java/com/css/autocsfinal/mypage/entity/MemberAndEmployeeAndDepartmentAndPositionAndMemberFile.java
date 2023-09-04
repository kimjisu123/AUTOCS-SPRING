package com.css.autocsfinal.mypage.entity;

import com.css.autocsfinal.member.entity.Department;
import com.css.autocsfinal.member.entity.Employee;
import com.css.autocsfinal.member.entity.Member;
import com.css.autocsfinal.member.entity.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TBL_EMPLOYEE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberAndEmployeeAndDepartmentAndPositionAndMemberFile {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REF_MEMBER_NO")
    private Member member;

    @ManyToOne()
    @JoinColumn(name = "REF_DEPARTMENT_CODE")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REF_POSITION_CODE")
    private Position position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REF_M_FILE_NO")
    private MemberFile memberFile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REF_EMPLOYEE_NO")
    private Employee employee;



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
                ", memberFile=" + memberFile +
                '}';
    }
}
