package com.css.autocsfinal.Approval.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BusinessDTO {

    private String businessTitle;
    private String businessNote;
    private int empNo;
    private String empName;
    private String business;
    private List<Integer> allow;
    private List<Integer> receive;
}

