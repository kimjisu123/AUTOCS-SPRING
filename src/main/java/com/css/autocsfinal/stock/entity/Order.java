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
public class Order {
    @Id
    @GeneratedValue(generator = "SEQ_ORDER_NO")
    @SequenceGenerator(name = "SEQ_ORDER_NO", sequenceName = "SEQ_ORDER_NO", allocationSize = 1)
    @Column(name = "ORDER_NO")
    private int orderNo;
    @Column(name = "REF_BILL_NO")
    private int refBillNo;
    @Column(name = "STORE_INFO_NO")
    private int storeInfoNo;
    @Column(name = "REGIST_DATE")
    private Date registDate;
    @Column(name = "STATUS")
    private String status;
}
