package com.css.autocsfinal.Approval.dto;

import lombok.*;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PayListDTO {

    private String documentTitle;
    private List<Date> payDate;
    private List<String> payReason;
    private List<Integer> payPrice;
    private int empNo;
    private String empName;
    private List<Integer> allow;
    private List<Integer> receive;
}
