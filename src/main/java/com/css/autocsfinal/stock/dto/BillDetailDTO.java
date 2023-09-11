package com.css.autocsfinal.stock.dto;

import com.css.autocsfinal.stock.entity.Company;
import com.css.autocsfinal.stock.entity.Order;
import com.css.autocsfinal.stock.entity.StoreInfomation;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BillDetailDTO {

    private int billNo;
    private Date registDate;
    private Company refCompanyNo;
    private StoreInfomation refStoreInfoNo;
    private Order refOrderNo;


}
