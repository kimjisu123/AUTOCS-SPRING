package com.css.autocsfinal.stock.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class IoDTO {
    private int productIoNo;
    private int quantity;
    private String io;
    private Date registDate;
    private int refProductNo;
}
