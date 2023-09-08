package com.css.autocsfinal.stock.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "TBL_PRODUCT_IO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class IoDetail {
    @Id
    @Column(name = "PRODUCT_IO_NO")
    private int productIoNo;
    @Column(name = "QUANTITY")
    private int quantity;
    @Column(name = "IO")
    private String io;
    @Column(name = "REGIST_DATE")
    private Date registDate;

    @ManyToOne
    @JoinColumn(name = "REF_PRODUCT_NO")
    private ProductDetail refProductNo;
}
