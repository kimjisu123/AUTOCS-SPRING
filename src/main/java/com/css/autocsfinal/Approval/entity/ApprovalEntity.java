package com.css.autocsfinal.Approval.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    public ApprovalEntity(int documentCode, int employeeNo) {
        this.documentCode = documentCode;
        this.employeeNo = employeeNo;
    }
}
