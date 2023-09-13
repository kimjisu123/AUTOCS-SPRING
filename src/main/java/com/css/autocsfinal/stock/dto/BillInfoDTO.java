package com.css.autocsfinal.stock.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BillInfoDTO {

    private int billNo;
    private int orderNo;
    private String storeInfoName;
    private String registDate;

}
