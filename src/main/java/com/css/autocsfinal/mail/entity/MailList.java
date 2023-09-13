package com.css.autocsfinal.mail.entity;

import com.css.autocsfinal.member.entity.Employee;
import com.css.autocsfinal.workstatus.entity.EmployeeByWorkStatus;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "TBL_MAIL_LIST")
@IdClass(MailListPK.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
 @ToString
public class MailList {

    @Id
    @Column(name="EMPLOYEE_NO")
    private int employeeNo;

    @Id
    @Column(name ="MAIL_NO")
    private int mailNo;

    @ManyToOne
    @JoinColumn(name="MAIL_NO", insertable = false, updatable = false)
    private Mail mail;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_NO", insertable = false, updatable = false)
    private EmployeeByWorkStatus employee;

    public MailList(int employeeNo, int mailNo) {
        this.employeeNo = employeeNo;
        this.mailNo = mailNo;
    }
}
