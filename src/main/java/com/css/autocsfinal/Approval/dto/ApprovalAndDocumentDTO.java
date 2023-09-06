package com.css.autocsfinal.Approval.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ApprovalAndDocumentDTO {

    private int approvalCode;
    private DocumentAndEmployeeDTO document;
    private int employeeNo;
    private String status;
    private Date timeStamp;
    private String cmt;
}
