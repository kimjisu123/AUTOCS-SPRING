package com.css.autocsfinal.Approval.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PayDTO {

    private int billingCode;
    private Date day;
    private int price;
    private int documentCode;
    private String business;
}
