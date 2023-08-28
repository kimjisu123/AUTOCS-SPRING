package com.css.autocsfinal.stock.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "TBL_PRODUCT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    @Id
    @GeneratedValue(generator = "SEQ_PRODUCT_NO")
    @SequenceGenerator(name = "SEQ_PRODUCT_NO", sequenceName = "SEQ_PRODUCT_NO", allocationSize = 1)
    @Column(name = "PRODUCT_NO")
    private int productNo;
    @Column(name = "NAME")
    private String name;
    @Column(name = "STOCK")
    private int stock;
    @Column(name = "PRICE")
    private int price;
    @Column(name = "ETC")
    private String etc;
    @Column(name = "REGIST_DATE")
    private Date registDate;
    @Column(name = "UNUSED_DATE")
    private Date unusedDate;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "REF_PRODUCT_CATEGORY_NO")
    private int refProductCategoryNo;
    @Column(name = "REF_PRODUCT_STANDARD_NO")
    private int refProductStandardNo;
    @Column(name = "REF_PRODUCT_UNIT_NO")
    private int refProductUnitNo;
}
