package com.css.autocsfinal.mail.entity;

import com.css.autocsfinal.member.entity.Employee;
import lombok.*;
import org.springframework.data.annotation.TypeAlias;

import javax.persistence.*;

@Entity
@Table(name = "TBL_MAIL_LIST")
@IdClass(MailListPK.class)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MailList {

    @Id
    @Column(name="EMPLOYEE_NO")
    private int employeeNo;

    @Id
    @Column(name ="MAIL_NO")
    private int mailNo;

    @ManyToOne
    @JoinColumn(name="EMPLOYEE_NO", insertable = false, updatable = false)
    private EmployeeByMail employee;

    @ManyToOne
    @JoinColumn(name="MAIL_NO", insertable = false, updatable = false)
    private Mail mail;
}
