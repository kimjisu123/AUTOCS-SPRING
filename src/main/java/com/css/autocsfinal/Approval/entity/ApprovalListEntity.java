package com.css.autocsfinal.Approval.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TBL_APPROVAL_LIST")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SequenceGenerator(
        name = "SEQ_APPROVAL_LIST_GENERATOR",
        sequenceName = "SEQ_APPROVAL_LIST_NO",
        initialValue = 1, allocationSize = 1
)
public class ApprovalListEntity {

    @Id
    @Column(name = "APPROVAL_LIST_CODE")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_APPROVAL_LIST_GENERATOR"
    )
    private int approvalListCode;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "TIME_STAMP")
    private Date timeStamp;

    @Column(name = "CMT")
    private String cmt;

    @Column(name = "APPROVAL_CODE")
    private int approvalCode;

    public ApprovalListEntity(String status, Date timeStamp, String cmt, int approvalCode) {
        this.status = status;
        this.timeStamp = timeStamp;
        this.cmt = cmt;
        this.approvalCode = approvalCode;
    }
}
