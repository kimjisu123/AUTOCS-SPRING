package com.css.autocsfinal.stock.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "TBL_ORDER")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetail {
    @Id
    @Column(name = "ORDER_NO")
    private int orderNo;

//    @ManyToOne
//    @JoinColumn(name = "REF_BILL_NO")
//    private Bill refBillNo;
    @Column(name = "REF_BILL_NO")
    private int refBillNo;

    @ManyToOne
    @JoinColumn(name = "STORE_INFO_NO")
    private StoreInfomation storeInfoNo;

    @Column(name = "REGIST_DATE")
    private Date registDate;
    @Column(name = "STATUS")
    private String status;
}
