package com.css.autocsfinal.Approval.dto;

import com.css.autocsfinal.member.dto.EmpPosiDeptDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RecEmpDTO {

    private EmpPosiDeptDTO employee;
    private int documentCode;
    private String status;
}
