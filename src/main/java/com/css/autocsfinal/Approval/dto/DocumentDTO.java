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

    private int documentCode;
    private String writer;
    private Date applicationDate;
    private String documentType;
    private String status;
}
