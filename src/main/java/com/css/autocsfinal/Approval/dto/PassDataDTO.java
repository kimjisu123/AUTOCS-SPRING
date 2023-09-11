package com.css.autocsfinal.Approval.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PassDataDTO {

    private int documentCode;
    private Date applicationDate;
    private String documentType;
    private String documentTitle;
    private int fileNum;
    private String status;
}
