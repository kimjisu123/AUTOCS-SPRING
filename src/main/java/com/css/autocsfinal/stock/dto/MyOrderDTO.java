package com.css.autocsfinal.stock.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MyOrderDTO {
    private int orderNo;
    private int refBillNo;
    private int storeInfoNo;
    private String registDate;
    private String status;
}
