package com.css.autocsfinal.Approval.dto;

import com.css.autocsfinal.member.dto.EmpPosiDeptDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class AppEmpDTO {

    private int approvalCode;
    private int documentCode;
    private String status;
    private String cmt;
    private EmpPosiDeptDTO employee;
}
