package com.css.autocsfinal.stock.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BillDTO {

    private int billNo;
    private java.util.Date registDate;
    private int refCompanyNo;
    private int refStoreInfoNo;


}
