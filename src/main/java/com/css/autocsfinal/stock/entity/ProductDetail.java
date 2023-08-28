package com.css.autocsfinal.stock.entity;

import com.css.autocsfinal.stock.dto.StandardDTO;
import com.css.autocsfinal.stock.dto.UnitDTO;
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
public class ProductDetail {
    @Id
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

    @ManyToOne
    @JoinColumn(name = "REF_PRODUCT_CATEGORY_NO")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "REF_PRODUCT_STANDARD_NO")
    private Standard standard;

    @ManyToOne
    @JoinColumn(name = "REF_PRODUCT_UNIT_NO")
    private Unit unit;
}
