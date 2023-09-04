package com.css.autocsfinal.stock.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderProductDTO {
    private int orderProductNo;
    private int quantity;
    private String status;
    private Date registDate;
    private int refOrderNo;
    private int refProductNo;

}
