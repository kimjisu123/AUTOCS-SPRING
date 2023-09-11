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
public class OrderProductDetail {
    @Id
    @Column(name = "ORDER_PRODUCT_NO")
    private int orderProductNo;
    @Column(name = "QUANTITY")
    private int quantity;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "REGIST_DATE")
    private Date registDate;

    @ManyToOne
    @JoinColumn(name = "REF_ORDER_NO")
    private OrderDetail refOrderNo;

    @ManyToOne
    @JoinColumn(name = "REF_PRODUCT_NO")
    private ProductDetail refProductNo;

    @Column(name = "ETC")
    private String etc;
}
