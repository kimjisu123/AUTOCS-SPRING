package com.css.autocsfinal.Approval.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DocumentDTO {

    private String documentTitle;
    private int documentCode;
    private int employeeNo;
    private Date applicationDate;
    private String documentType;
    private String status;
}
