package com.css.autocsfinal.stock.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderListDTO {
    private Integer orderNo;
    private String storeInfoName;
    private String categoryName;
    private String productName;
    private String unitName;
    private String standardName;
    private int price;
    private int quantity;
    private String etc;
    private Date registDate;
    private String status;
}
