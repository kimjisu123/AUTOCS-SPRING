package com.css.autocsfinal.Approval.dto;

import com.css.autocsfinal.member.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DocumentAndEmployeeDTO {

    private String documentTitle;
    private int documentCode;
    private EmployeeDTO employee;
    private Date applicationDate;
    private String documentType;
    private String status;
}
