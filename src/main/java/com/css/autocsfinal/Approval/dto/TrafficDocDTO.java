package com.css.autocsfinal.Approval.dto;

import com.css.autocsfinal.member.dto.EmpPosiDeptDTO;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TrafficDocDTO {

    private int documentCode;
    private EmpPosiDeptDTO employee;
    private Date applicationDate;
    private String documentType;
    private String status;
    private String documentTitle;
    private List<AppEmpDTO> appEmp;
    private List<RecEmpDTO> recEmp;
    private List<TrafficDocListDTO> traffic;
    private List<DocumentFileDTO> files;
}
