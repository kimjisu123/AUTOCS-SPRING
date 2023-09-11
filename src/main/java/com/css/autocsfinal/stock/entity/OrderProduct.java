package com.css.autocsfinal.stock.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "TBL_ORDER_PRODUCT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderProduct {
    @Id
    @GeneratedValue(generator = "SEQ_ORDER_PRODUCT_NO")
    @SequenceGenerator(name = "SEQ_ORDER_PRODUCT_NO", sequenceName = "SEQ_ORDER_PRODUCT_NO", allocationSize = 1)
    @Column(name = "ORDER_PRODUCT_NO")
    private int orderProductNo;
    @Column(name = "QUANTITY")
    private int quantity;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "REGIST_DATE")
    private Date registDate;
    @Column(name = "REF_ORDER_NO")
    private int refOrderNo;
    @Column(name = "REF_PRODUCT_NO")
    private int refProductNo;
    @Column(name = "ETC")
    private String etc;
}
