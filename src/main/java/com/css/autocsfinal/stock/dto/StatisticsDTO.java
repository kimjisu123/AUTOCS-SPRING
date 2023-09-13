package com.css.autocsfinal.stock.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StatisticsDTO {

    private Integer productNo;
    private String categoryName;
    private String productName;
    private String standardName;
    private String unitName;
    private int price;
    private Integer completeQuantity;
    private Integer refundQuantity;

}
