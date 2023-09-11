package com.css.autocsfinal.Approval.dto;

import com.css.autocsfinal.member.dto.EmpPosiDeptDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VacationDocDTO {

    private int documentCode;
    private EmpPosiDeptDTO employee;
    private Date applicationDate;
    private String documentType;
    private String status;
    private String documentTitle;
    private List<AppEmpDTO> appEmp;
    private List<RecEmpDTO> recEmp;
    private VacationListDTO vacation;
    private List<DocumentFileDTO> files;
}
