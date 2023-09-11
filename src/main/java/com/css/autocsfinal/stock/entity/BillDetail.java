package com.css.autocsfinal.stock.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "TBL_BILL")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BillDetail {

    @Id
    @Column(name = "BILL_NO")
    private int billNo;
    @Column(name = "REGIST_DATE")
    private Date registDate;

    @ManyToOne
    @JoinColumn(name = "REF_COMPANY_NO")
    private Company refCompanyNo;
    @ManyToOne
    @JoinColumn(name = "REF_STORE_INFO_NO")
    private StoreInfomation refStoreInfoNo;
    @ManyToOne
    @JoinColumn(name = "REF_ORDER_NO")
    private Order refOrderNo;

}
