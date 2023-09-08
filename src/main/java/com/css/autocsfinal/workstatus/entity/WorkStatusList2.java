package com.css.autocsfinal.workstatus.entity;

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
public class WorkStatusList2 {

    @Id
    @Column(name="EMPLOYEE_NO")
    private int employeeNo;

    @Id
    @Column(name ="WORK_STATUS_CODE")
    private int workStatusCode;

    @ManyToOne
    @JoinColumn(name="WORK_STATUS_CODE", insertable = false, updatable = false)
    private WorkStatus workStatus;

    public WorkStatusList2(int employeeNo, int workStatusCode) {
        this.employeeNo = employeeNo;
        this.workStatusCode = workStatusCode;
    }

}
