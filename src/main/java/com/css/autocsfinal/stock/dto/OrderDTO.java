package com.css.autocsfinal.stock.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTO {
    private int orderNo;
    private int refBillNo;
    private int storeInfoNo;
    private Date registDate;
}
