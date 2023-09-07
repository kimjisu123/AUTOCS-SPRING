package com.css.autocsfinal.Approval.dto;

import com.css.autocsfinal.Approval.entity.BusinessEntity;
import com.css.autocsfinal.member.dto.EmpPosiDeptDTO;
import com.css.autocsfinal.member.dto.EmployeeDTO;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class BusinessDocDTO {

    private int documentCode;
    private EmpPosiDeptDTO employee;
    private Date applicationDate;
    private String documentType;
    private String status;
    private String documentTitle;
    private List<AppEmpDTO> appEmp;
    private List<RecEmpDTO> recEmp;
    private BusinessDTO business;
    private List<DocumentFileDTO> files;
}
