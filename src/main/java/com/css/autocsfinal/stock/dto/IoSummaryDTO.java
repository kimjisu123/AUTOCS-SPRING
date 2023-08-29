package com.css.autocsfinal.stock.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class IoSummaryDTO {
    private int refProductNo;
    private String io;
    private long totalQuantity;
    private String productName;
    private String categoryName;
    private String standardName;
    private String unitName;
    private int stock;
    private int price;
    private String etc;
}
