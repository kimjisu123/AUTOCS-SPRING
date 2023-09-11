package com.css.autocsfinal.stock.dto;

import com.css.autocsfinal.stock.entity.Bill;
import com.css.autocsfinal.stock.entity.StoreInfomation;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetailDTO {
    private int orderNo;

//    private Bill refBillNo;
    private int refBillNo;

    private StoreInfomation storeInfoNo;
    private Date registDate;
    private String status;
}
