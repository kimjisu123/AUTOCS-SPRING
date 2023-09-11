package com.css.autocsfinal.Approval.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TBL_APPROVAL")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ApprovalAndDocumentEntity {

    @Id
    @Column(name = "APPROVAL_CODE")
    private int approvalCode;

    @OneToOne
    @JoinColumn(name = "DOCUMENT_CODE")
    private DocumentEmployeeEntity document;

    @Column(name = "EMPLOYEE_NO")
    private int employeeNo;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "TIME_STAMP")
    private Date timeStamp;

    @Column(name = "CMT")
    private String cmt;
}
