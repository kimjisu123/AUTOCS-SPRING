package com.css.autocsfinal.mail.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
public class EmployeeByMail {

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

    @OneToMany
    @JoinColumn(name = "MAIL_NO")
    private List<Mail> mailList;
}