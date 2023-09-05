package com.css.autocsfinal.Approval.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class VacationListDTO {

    private String documentTitle;
    private String vacationType;
    private String startDate;
    private String endDate;
    private String half;
    private String vacationReason;
    private List<Integer> allow;
    private List<Integer> receive;
    private int empNo;
    private String empName;
}
