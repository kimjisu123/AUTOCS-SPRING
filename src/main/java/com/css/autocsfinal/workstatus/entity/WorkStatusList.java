package com.css.autocsfinal.workstatus.entity;

import com.css.autocsfinal.mail.entity.Mail;
import com.css.autocsfinal.member.entity.Employee;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name ="TBL_WORK_STATUS_LIST")
@IdClass(WorkStatusListPk.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class WorkStatusList {

    @Id
    @Column(name="EMPLOYEE_NO")
    private int employeeNo;

    @Id
    @Column(name ="WORK_STATUS_CODE")
    private int workStatusCode;

    @ManyToOne
    @JoinColumn(name="WORK_STATUS_CODE", insertable = false, updatable = false)
    private WorkStatus workStatus;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_NO", insertable = false, updatable = false)
    private EmployeeByWorkStatus employee;


    public WorkStatusList(int employeeNo, int workStatusCode) {
        this.employeeNo = employeeNo;
        this.workStatusCode = workStatusCode;
    }

}
