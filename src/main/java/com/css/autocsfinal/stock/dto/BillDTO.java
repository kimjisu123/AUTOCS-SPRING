package com.css.autocsfinal.stock.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BillDTO {

    private int billNo;
    private Date registDate;
    private int refCompanyNo;
    private int refStoreInfoNo;


}
