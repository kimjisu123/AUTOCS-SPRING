package com.css.autocsfinal.stock.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class IoSummaryDTO {
    private int refProductNo;
    private String io;
    private int totalQuantity;
}
