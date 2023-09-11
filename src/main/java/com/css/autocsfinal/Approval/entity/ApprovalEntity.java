package com.css.autocsfinal.Approval.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TBL_APPROVAL")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SequenceGenerator(
        name = "SEQ_APPROVAL_GENERATOR",
        sequenceName = "SEQ_APPROVAL_NO",
        initialValue = 1, allocationSize = 1
)
public class ApprovalEntity {

    @Id
    @Column(name = "APPROVAL_CODE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_APPROVAL_GENERATOR")
    private int approvalCode;

    @Column(name = "DOCUMENT_CODE")
    private int documentCode;

    @Column(name = "EMPLOYEE_NO")
    private int employeeNo;


    @Column(name = "STATUS")
    private String status;

    @Column(name = "TIME_STAMP")
    private Date timeStamp;

    @Column(name = "CMT")
    private String CMT;

    public ApprovalEntity(int documentCode, int employeeNo, String status, Date timeStamp, String CMT) {
        this.documentCode = documentCode;
        this.employeeNo = employeeNo;
        this.status = status;
        this.timeStamp = timeStamp;
        this.CMT = CMT;
    }
}
